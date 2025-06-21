package eu.cokeman.cycleareastats.out.persistence.jpa.repository;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
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

    public AdministrativeAreaRepository(JpaAdministrativeAreaRepositorySpringDataRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public AdministrativeArea findByAdministrativeAreaId(AdministrativeAreaId administrativeAreaId) {
        var jpaLandmark = springDataRepository.findById(administrativeAreaId.value());
        AdministrativeArea administrativeArea = mapper.mapJpaToInternal(jpaLandmark.orElseThrow()).build();
        return administrativeArea;
    }

    @Override
    public List<AdministrativeArea> findSubUnits(AdministrativeAreaId administrativeAreaId) {

        return null;
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
    public AdministrativeAreaId findParent(AdministrativeAreaId administrativeAreaId) {

        return null;
    }

    @Override
    public List<AdministrativeArea> findByCountry(Country country) {
        return List.of();
    }

    @Override
    public List<AdministrativeArea> filterAdministrativeAreas(String criteria) {
        return List.of();
    }

    @Override
    public AdministrativeAreaId importLandmark(AdministrativeArea administrativeArea) {

        var jpaLandmark = mapper.mapToJpa(administrativeArea);

        var generatedEntity = springDataRepository.save(jpaLandmark);

        return new AdministrativeAreaId(generatedEntity.getId());
    }
}
