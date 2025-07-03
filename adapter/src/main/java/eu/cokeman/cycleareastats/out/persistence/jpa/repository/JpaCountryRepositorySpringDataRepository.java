package eu.cokeman.cycleareastats.out.persistence.jpa.repository;

import eu.cokeman.cycleareastats.out.persistence.jpa.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCountryRepositorySpringDataRepository
    extends JpaRepository<CountryEntity, Integer> {
  CountryEntity findByName(String name);
}
