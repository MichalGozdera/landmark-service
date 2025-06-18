package eu.cokeman.cycleareastats.out.persistence.jpa.repository;

import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.mapper.country.CountryExternalMapper;
import eu.cokeman.cycleareastats.mapper.country.CountryJpaMapper;
import eu.cokeman.cycleareastats.valueObject.CountryId;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.CountryEntity;
import eu.cokeman.cycleareastats.mapper.country.CountryCommonMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CountryRepository implements eu.cokeman.cycleareastats.port.out.persistence.CountryRepository {
    private final JpaCountryRepositorySpringDataRepository jpaRepository;

    public CountryRepository(JpaCountryRepositorySpringDataRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Country findById(CountryId id) {
        CountryEntity entity = jpaRepository.findById(id.value()).orElseThrow();
        return CountryJpaMapper.INSTANCE.mapJpaToInternal(entity).build();
    }

    @Override
    public Optional<Country> findByName(String name) {
        CountryEntity entity = jpaRepository.findByName(name);
        return entity != null ? Optional.of(CountryJpaMapper.INSTANCE.mapJpaToInternal(entity).build()) : Optional.empty();
    }

    @Override
    public List<Country> findAll() {
        return jpaRepository.findAll().stream().map(r->CountryJpaMapper.INSTANCE.mapJpaToInternal(r).build()).toList();
    }

    @Override
    public CountryId create(Country country) {
        CountryEntity entity = CountryJpaMapper.INSTANCE.mapToJpa(country);
        CountryEntity saved = jpaRepository.save(entity);
        return new CountryId(saved.getId());
    }

    @Override
    public void delete(CountryId id) {
        jpaRepository.deleteById(id.value());
    }

    @Override
    public Country update(CountryId id, Country country) {
        CountryEntity entity = jpaRepository.findById(id.value()).orElseThrow();
        entity.setName(country.getName());
        CountryEntity updated = jpaRepository.save(entity);
        return CountryJpaMapper.INSTANCE.mapJpaToInternal(updated).build();
    }
}
