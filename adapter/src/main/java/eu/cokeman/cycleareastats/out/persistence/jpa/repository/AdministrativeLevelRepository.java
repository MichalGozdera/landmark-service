package eu.cokeman.cycleareastats.out.persistence.jpa.repository;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.mapper.level.AdministrativeLevelJpaMapper;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeLevelEntity;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.BaseJpaEntity;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.CountryEntity;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;
import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.valueObject.LevelName;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdministrativeLevelRepository implements eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository {

    private static final Logger log = LoggerFactory.getLogger(AdministrativeLevelRepository.class);
    AdministrativeLevelJpaMapper mapper = AdministrativeLevelJpaMapper.INSTANCE;

    private final JpaAdministrativeLevelRepositorySpringDataRepository levelSpringDataRepository;

    public AdministrativeLevelRepository(JpaAdministrativeLevelRepositorySpringDataRepository springDataRepository, JpaCountryRepositorySpringDataRepository countrySpringDataRepository) {
        this.levelSpringDataRepository = springDataRepository;
    }


    @Override
    public AdministrativeLevel findByAdministrativeLevelId(AdministrativeLevelId administrativeLevelId) {
        var entity = levelSpringDataRepository.findById(administrativeLevelId.value());
        return mapper.mapJpaToInternal(entity.get()).build();

    }

    @Override
    public AdministrativeLevel updateLevel(AdministrativeLevelId levelId, AdministrativeLevel level) {

        var jpaLevel = levelSpringDataRepository.findById(levelId.value()).orElseThrow(EntityNotFoundException::new);
        AdministrativeLevelEntity newJpa = mapper.mapToJpa(level);
        BeanUtils.copyProperties(newJpa, jpaLevel, BaseJpaEntity.getNullPropertyNames(newJpa));
        var updatedJPA = levelSpringDataRepository.saveAndFlush(jpaLevel);
        var result = mapper.mapJpaToInternal(updatedJPA).build();

        return result;
    }

    @Override
    public void deleteLevel(AdministrativeLevelId administrativeLevelId) {
        levelSpringDataRepository.deleteById(administrativeLevelId.value());
    }

    @Override
    public Optional<AdministrativeLevel> findByCountryAndName(Country country, LevelName name) {
        var foundEntity = levelSpringDataRepository.findByCountry_NameAndName(country.getName(), name.name());
        return foundEntity.map(entity -> mapper.mapJpaToInternal(entity).build());
    }

    @Override
    public AdministrativeLevelId createLevel(AdministrativeLevel administrativeLevel) {
        var jpaLevel = mapper.mapToJpa(administrativeLevel);
        var newID = levelSpringDataRepository.save(jpaLevel).getId();

        return new AdministrativeLevelId(newID);
    }
}
