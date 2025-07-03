package eu.cokeman.cycleareastats.out.persistence.jpa.repository;

import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeLevelEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAdministrativeLevelRepositorySpringDataRepository
    extends JpaRepository<AdministrativeLevelEntity, Integer> {

  Optional<AdministrativeLevelEntity> findByCountry_NameAndName(String country_name, String name);
}
