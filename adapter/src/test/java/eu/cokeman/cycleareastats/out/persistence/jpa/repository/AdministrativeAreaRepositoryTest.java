package eu.cokeman.cycleareastats.out.persistence.jpa.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.cokeman.cycleareastats.mapper.area.AdministrativeAreaJpaMapper;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeAreaEntity;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeLevelEntity;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.CountryEntity;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.AreaName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class AdministrativeAreaRepositoryTest {

    @Autowired
    private AdministrativeAreaRepository repository;

    @Autowired
    private JpaCountryRepositorySpringDataRepository countryRepository;

    @Autowired
    private JpaAdministrativeLevelRepositorySpringDataRepository levelRepository;

    @Test
    void testFindByLevel_NameAndLevel_Country_Name() throws Exception {
        var country = saveCountry("Poland");
        var level = saveLevel("voivodeship", country, 1);
        var area = prepareAreaEntity("parent-area.json", "Test Area", level, null);
        var domainArea = AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(area).build();
        repository.createArea(domainArea);
        var result = repository.findByLevelAndCountry("voivodeship", "Poland");
        assertThat(result.stream().map(a -> a.getName().name()).toList()).contains("Test Area");
    }

    @Test
    void testFindByMetadataContaining() throws Exception {
        var area = prepareAreaEntity("parent-area.json", "Fragment Test Area", null, "{\"desc\":\"fragment description\"}");
        var domainArea = AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(area).build();
        repository.createArea(domainArea);
        var result = repository.findByMetadataContains("fragment");
        assertThat(result.stream().map(a -> a.getName().name()).toList()).contains("Fragment Test Area");
    }

    @Test
    void testFindParentIdWithoutExplicitParentRelation() throws Exception {
        var country = saveCountry("Poland");
        var parentLevel = saveLevel("parent-level", country, 1);
        var childLevel = saveLevel("child-level", country, 2);
        var parentArea = prepareAreaEntity("parent-area.json", "Parent Area", parentLevel, null);
        var childArea = prepareAreaEntity("child-area.json", "Child Area", childLevel, null);
        var parentId = repository.createArea(AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(parentArea).build());
        var childId = repository.createArea(AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(childArea).build());
        var foundParentId = repository.findParent(childId);
        assertThat(foundParentId).isEqualTo(parentId);
    }

    @Test
    void testFindChildrenWithoutExplicitParentRelation() throws Exception {
        var country = saveCountry("Poland");
        var parentLevel = saveLevel("parent-level", country, 1);
        var childLevel = saveLevel("child-level", country, 2);
        var parentArea = prepareAreaEntity("parent-area.json", "Parent Area", parentLevel, null);
        var childArea = prepareAreaEntity("child-area.json", "Child Area", childLevel, null);
        var parentId = repository.createArea(AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(parentArea).build());
        repository.createArea(AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(childArea).build());
        var children = repository.findChildren(parentId);
        assertThat(children.stream().map(a -> a.getName().name()).toList()).contains("Child Area");
    }

    @Test
    void testFindChildrenOnlyInsideParentGeometry() throws Exception {
        var country = saveCountry("Poland");
        var parentLevel = saveLevel("parent-level", country, 1);
        var childLevel = saveLevel("child-level", country, 2);
        var parentArea = prepareAreaEntity("parent-area.json", "Parent Area", parentLevel, null);
        var childInside = prepareAreaEntity("child-area.json", "Child Inside", childLevel, null);
        var childOutside = prepareAreaEntity("child-area-outside.json", "Child Outside", childLevel, null);
        var parentId = repository.createArea(AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(parentArea).build());
        repository.createArea(AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(childInside).build());
        repository.createArea(AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(childOutside).build());
        var children = repository.findChildren(parentId);
        var names = children.stream().map(a -> a.getName().name()).toList();
        assertThat(names).contains("Child Inside");
        assertThat(names).doesNotContain("Child Outside");
    }

    @Test
    void testFindParentIdMultipleParentsReturnsNull() throws Exception {
        var country = saveCountry("Poland");
        var parentLevel = saveLevel("parent-level", country, 1);
        var childLevel = saveLevel("child-level", country, 2);
        var parentArea1 = prepareAreaEntity("parent-area.json", "Parent 1", parentLevel, null);
        var parentArea2 = prepareAreaEntity("parent-area.json", "Parent 2", parentLevel, null);
        var childArea = prepareAreaEntity("child-area.json", "Child Area", childLevel, null);
        repository.createArea(AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(parentArea1).build());
        repository.createArea(AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(parentArea2).build());
        var childId = repository.createArea(AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(childArea).build());
        var parentId = repository.findParent(childId);
        assertThat(parentId).isNull();
    }

    @Test
    void testCreateAreaPersistsAndReturnsId() throws Exception {
        var area = prepareAreaEntity("parent-area.json", "Create Test Area", null, null);
        var domainArea = AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(area).build();
        var id = repository.createArea(domainArea);
        var found = repository.findByAdministrativeAreaId(id);
        assertThat(found.getName().name()).isEqualTo("Create Test Area");
    }

    @Test
    void testUpdateAreaUpdatesExisting() throws Exception {
        var area = prepareAreaEntity("parent-area.json", "Update Test Area", null, null);
        var domainArea = AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(area).build();
        var id = repository.createArea(domainArea);
        var found = repository.findByAdministrativeAreaId(id);
        var updated = found.toBuilder().name(new AreaName("Updated Area")).build();
        repository.updateAdministrativeArea(id, updated);
        var afterUpdate = repository.findByAdministrativeAreaId(id);
        assertThat(afterUpdate.getName().name()).isEqualTo("Updated Area");
    }

    @Test
    void testDeleteAreaRemovesArea() throws Exception {
        var area = prepareAreaEntity("parent-area.json", "Delete Test Area", null, null);
        var domainArea = AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(area).build();
        var id = repository.createArea(domainArea);
        repository.deleteAdministrativeArea(id);
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> repository.findByAdministrativeAreaId(id)).isInstanceOf(Exception.class);
    }

    @Test
    void testFindSimpleByIdReturnsSimple() throws Exception {
        var area = prepareAreaEntity("parent-area.json", "Simple Test Area", null, null);
        var domainArea = AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(area).build();
        var id = repository.createArea(domainArea);
        var simple = repository.findSimpleByAdministrativeAreaId(id);
        assertThat(simple).isNotNull();
        assertThat(simple.getId()).isEqualTo(id);
        assertThat(simple.getName().name()).isEqualTo("Simple Test Area");
    }

    @Test
    void testFindSimpleByIdReturnsSimpleWithoutGeometry() throws Exception {
        var area = prepareAreaEntity("parent-area.json", "Simple Test Area", null, null);
        var domainArea = AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(area).build();
        var id = repository.createArea(domainArea);
        var simple = repository.findSimpleByAdministrativeAreaId(id);
        assertThat(simple).isNotNull();
        assertThat(simple.getId()).isEqualTo(id);
        assertThat(simple.getName().name()).isEqualTo("Simple Test Area");
        assertThat(simple.getGeometry()).isNull();
    }

    @Test
    void testFindByAdministrativeAreaIdReturnsFullWithGeoJsonGeometry() throws Exception {
        var area = prepareAreaEntity("parent-area.json", "Full Test Area", null, null);
        var domainArea = AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(area).build();
        var id = repository.createArea(domainArea);
        var found = repository.findByAdministrativeAreaId(id);
        assertThat(found.getName().name()).isEqualTo("Full Test Area");
        assertThat(found.getGeometry()).isNotNull();
        String geoJson = found.getGeometry().toString();
        assertThat(geoJson).isNotEmpty();
    }

    @Test
    void testFindSimpleByIdThrowsForNonExisting() {
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> repository.findSimpleByAdministrativeAreaId(new AdministrativeAreaId(-999))).isInstanceOf(Exception.class);
    }

    @Test
    void testFindSimpleByLevelAndCountryReturnsSimpleList() throws Exception {
        var country = saveCountry("Poland");
        var level = saveLevel("voivodeship", country, 1);
        var area = prepareAreaEntity("parent-area.json", "Simple List Area", level, null);
        var domainArea = AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(area).build();
        repository.createArea(domainArea);
        var result = repository.findSimpleByLevelAndCountry("voivodeship", "Poland");
        assertThat(result.stream().map(a -> a.getName().name()).toList()).contains("Simple List Area");
        assertThat(result.stream().allMatch(a -> a.getGeometry() == null)).isTrue();
    }

    @Test
    void testFindSimpleByLevelAndCountryReturnsEmpty() {
        var result = repository.findSimpleByLevelAndCountry("no-such-level", "no-such-country");
        assertThat(result).isEmpty();
    }

    @Test
    void testFindSimpleByMetadataContainsReturnsSimpleList() throws Exception {
        var area = prepareAreaEntity("parent-area.json", "Simple Metadata Area", null, "{\"desc\":\"meta-fragment\"}");
        var domainArea = AdministrativeAreaJpaMapper.INSTANCE.mapJpaToInternal(area).build();
        repository.createArea(domainArea);
        var result = repository.findSimpleByMetadataContains("meta-fragment");
        assertThat(result.stream().map(a -> a.getName().name()).toList()).contains("Simple Metadata Area");
        assertThat(result.stream().allMatch(a -> a.getGeometry() == null)).isTrue();
    }

    @Test
    void testFindSimpleByMetadataContainsReturnsEmpty() {
        var result = repository.findSimpleByMetadataContains("no-such-metadata");
        assertThat(result).isEmpty();
    }

    private AdministrativeAreaEntity loadAreaFromJson(String resource) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getClassLoader().getResourceAsStream(resource);
        JsonNode node = mapper.readTree(is);
        AdministrativeAreaEntity entity = new AdministrativeAreaEntity();
        entity.setName(node.get("name").asText());
        if (node.has("geometry")) {
            GeoJsonReader geoJsonReader = new GeoJsonReader();
            entity.setGeometry(geoJsonReader.read(node.get("geometry").toString()));
        }
        return entity;
    }

    private CountryEntity saveCountry(String name) {
        var country = new CountryEntity();
        country.setName(name);
        return countryRepository.save(country);
    }

    private AdministrativeLevelEntity saveLevel(String name, CountryEntity country, int order) {
        var level = new AdministrativeLevelEntity();
        level.setName(name);
        level.setCountry(country);
        level.setOrder(order);
        return levelRepository.save(level);
    }

    private AdministrativeAreaEntity prepareAreaEntity(String jsonFile, String name, AdministrativeLevelEntity level, String metadata) throws Exception {
        AdministrativeAreaEntity area = loadAreaFromJson(jsonFile);
        area.setName(name);
        if (level != null) area.setLevel(level);
        if (metadata != null) area.setMetadata(metadata);
        return area;
    }

}
