package eu.cokeman.cycleareastats.out.persistence.jpa.repository;

import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeAreaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaAdministrativeAreaRepositorySpringDataRepository extends JpaRepository<AdministrativeAreaEntity, Integer> {

    List<AdministrativeAreaEntity> findByLevel_NameAndLevel_Country_Name(String levelName,  String countryName);

    @Query(value = "SELECT * FROM administrative_area WHERE metadata::text ILIKE %:metadataQuery%", nativeQuery = true)
    List<AdministrativeAreaEntity> findByMetadataContaining(@Param("metadataQuery") String metadataQuery);
  }