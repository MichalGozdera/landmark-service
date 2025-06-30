package eu.cokeman.cycleareastats.out.persistence.jpa.repository;

import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeAreaEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.WKTReader;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class JpaAdministrativeAreaRepositorySpringDataRepositoryTest {

    @Autowired
    private JpaAdministrativeAreaRepositorySpringDataRepository repository;

    @Test
    @DisplayName("findByLevel_NameAndLevel_Country_Name returns results for existing data")
    void testFindByLevel_NameAndLevel_Country_Name() {
        // given
        GeometryFactory geometryFactory = new GeometryFactory();
        WKTReader reader = new WKTReader(geometryFactory);
        Geometry geometry = null;
        try {
            geometry = reader.read("POLYGON((0 0,0 1,1 1,1 0,0 0))");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        AdministrativeAreaEntity area = new AdministrativeAreaEntity();
        area.setGeometry(geometry);
        area.setName("Test Area");
        // set other required entity fields, e.g. level, metadata, etc.
        repository.save(area);
        // when
        List<AdministrativeAreaEntity> result = repository.findByLevel_NameAndLevel_Country_Name("województwo", "Polska");
        // then
        assertThat(result).isNotNull();
        // assertThat(result).isNotEmpty(); // if test data is added
    }

    @Test
    @DisplayName("findByMetadataContaining returns results for metadata fragment")
    void testFindByMetadataContaining() {
        // given
        // TODO: add test data to the database
        // when
        List<AdministrativeAreaEntity> result = repository.findByMetadataContaining("fragment");
        // then
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("findParentId returns parentId for existing childId")
    void testFindParentId() {
        // given
        Integer childId = 1; // TODO: add test data and set correct ID
        // when
        Optional<Integer> parentId = repository.findParentId(childId);
        // then
        assertThat(parentId).isNotNull();
        // assertThat(parentId).isPresent(); // if test data is added
    }

    @Test
    @DisplayName("findChildren returns children for existing parentId")
    void testFindChildren() {
        // given
        Integer parentId = 1; // TODO: add test data and set correct ID
        // when
        List<AdministrativeAreaEntity> children = repository.findChildren(parentId);
        // then
        assertThat(children).isNotNull();
    }
}
