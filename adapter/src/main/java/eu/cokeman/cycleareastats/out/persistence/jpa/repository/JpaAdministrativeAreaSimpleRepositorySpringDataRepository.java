package eu.cokeman.cycleareastats.out.persistence.jpa.repository;

import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeAreaSimpleEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAdministrativeAreaSimpleRepositorySpringDataRepository
    extends JpaRepository<AdministrativeAreaSimpleEntity, Integer> {
  List<AdministrativeAreaSimpleEntity> findByLevel_NameAndLevel_Country_Name(
      String levelName, String countryName);

  @Query(
      value = "SELECT * FROM administrative_area WHERE metadata::text ILIKE %:metadataQuery%",
      nativeQuery = true)
  List<AdministrativeAreaSimpleEntity> findByMetadataContaining(
      @Param("metadataQuery") String metadataQuery);
}
