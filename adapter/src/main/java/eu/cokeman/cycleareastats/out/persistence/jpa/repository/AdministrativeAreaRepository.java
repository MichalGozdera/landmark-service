package eu.cokeman.cycleareastats.out.persistence.jpa.repository;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.mapper.area.AdministrativeAreaJpaMapper;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeAreaEntity;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.BaseJpaEntity;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.entity.Country;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdministrativeAreaRepository implements eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository {

    private static final Logger log = LoggerFactory.getLogger(AdministrativeAreaRepository.class);
    AdministrativeAreaJpaMapper mapper = AdministrativeAreaJpaMapper.INSTANCE;

    private final JpaAdministrativeAreaRepositorySpringDataRepository springDataRepository;
    private final JpaAdministrativeAreaSimpleRepositorySpringDataRepository springSimpleDataRepository;

    public AdministrativeAreaRepository(JpaAdministrativeAreaRepositorySpringDataRepository springDataRepository,
                                        JpaAdministrativeAreaSimpleRepositorySpringDataRepository simpleRepositorySpringDataRepository) {
        this.springDataRepository = springDataRepository;
        this.springSimpleDataRepository = simpleRepositorySpringDataRepository;
    }

    @Override
    public AdministrativeArea findByAdministrativeAreaId(AdministrativeAreaId administrativeAreaId) {
        var jpaLandmark = springDataRepository.findById(administrativeAreaId.value());
        AdministrativeArea administrativeArea = mapper.mapJpaToInternal(jpaLandmark.orElseThrow()).build();
        return administrativeArea;
    }

    @Override
    public AdministrativeArea findSimpleByAdministrativeAreaId(AdministrativeAreaId administrativeAreaId) {
        var jpaLandmark = springSimpleDataRepository.findById(administrativeAreaId.value());
        AdministrativeArea administrativeArea = mapper.mapSimpleJpaToInternal(jpaLandmark.orElseThrow()).build();
        return administrativeArea;
    }


    @Override
    public AdministrativeArea updateAdministrativeArea(AdministrativeAreaId areaId, AdministrativeArea administrativeArea) {
        var jpaLandmark = springDataRepository.findById(areaId.value()).orElseThrow(EntityNotFoundException::new);
        AdministrativeAreaEntity newJpa = mapper.mapToJpa(administrativeArea);
        BeanUtils.copyProperties(newJpa, jpaLandmark, BaseJpaEntity.getNullPropertyNames(newJpa));
        var updatedJPA = springDataRepository.saveAndFlush(jpaLandmark);
        var result = mapper.mapJpaToInternal(updatedJPA).build();
        return result;
    }

    @Override
    public void deleteAdministrativeArea(AdministrativeAreaId administrativeAreaId) {
        springDataRepository.deleteById(administrativeAreaId.value());
    }


    @Override
    public AdministrativeAreaId importLandmark(AdministrativeArea administrativeArea) {

        var jpaLandmark = mapper.mapToJpa(administrativeArea);

        var generatedEntity = springDataRepository.save(jpaLandmark);

        return new AdministrativeAreaId(generatedEntity.getId());
    }

    @Override
    public List<AdministrativeArea> findByLevelAndCountry(String levelName, String countryName) {
        var entities = springDataRepository.findByLevel_NameAndLevel_Country_Name(levelName, countryName);
        return entities.stream().map(e -> mapper.mapJpaToInternal(e).build()).toList();
    }

    @Override
    public List<AdministrativeArea> findByMetadataContains(String metadataQuery) {
        var entities = springDataRepository.findByMetadataContaining(metadataQuery);
        return entities.stream().map(e -> mapper.mapJpaToInternal(e).build()).toList();
    }

    @Override
    public List<AdministrativeArea> findSimpleByLevelAndCountry(String levelName, String countryName) {
        var entities = springSimpleDataRepository.findByLevel_NameAndLevel_Country_Name(levelName, countryName);
        return entities.stream().map(e -> mapper.mapSimpleJpaToInternal(e).build()).toList();
    }

    @Override
    public List<AdministrativeArea> findSimpleByMetadataContains(String metadataQuery) {
        var entities = springSimpleDataRepository.findByMetadataContaining(metadataQuery);
        return entities.stream().map(e -> mapper.mapSimpleJpaToInternal(e).build()).toList();
    }
}
