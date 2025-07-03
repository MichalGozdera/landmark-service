package eu.cokeman.cycleareastats.out.persistence.jpa.repository;

import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeAreaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAdministrativeAreaRepositorySpringDataRepository
    extends JpaRepository<AdministrativeAreaEntity, Integer> {

  List<AdministrativeAreaEntity> findByLevel_NameAndLevel_Country_Name(
      String levelName, String countryName);

  @Query(
      value = "SELECT * FROM administrative_area WHERE metadata::text ILIKE %:metadataQuery%",
      nativeQuery = true)
  List<AdministrativeAreaEntity> findByMetadataContaining(
      @Param("metadataQuery") String metadataQuery);

  @Query(
      value =
          "SELECT parent.id\n"
              + "FROM landmarks.administrative_area  child\n"
              + "JOIN landmarks.administrative_area parent\n"
              + "  ON parent.level_id = (\n"
              + "      SELECT id FROM landmarks.administrative_level\n"
              + "      WHERE level_order  = (\n"
              + "          SELECT l.level_order - 1\n"
              + "          FROM landmarks.administrative_level l\n"
              + "          WHERE l.id = child.level_id\n"
              + "      )\n"
              + "      AND country  = (\n"
              + "          SELECT country FROM landmarks.administrative_level l WHERE l.id = child.level_id\n"
              + "      )\n"
              + "  )\n"
              + "WHERE child.id = :childId\n"
              + "  AND ST_Area(ST_Intersection(ST_MakeValid(parent.geometry), ST_MakeValid(child.geometry))) / ST_Area(ST_MakeValid(child.geometry)) > 0.98",
      nativeQuery = true)
  Optional<Integer> findParentId(@Param("childId") Integer childId);

  @Query(
      value =
          "SELECT child.*\n"
              + "FROM landmarks.administrative_area parent\n"
              + "JOIN landmarks.administrative_area child ON child.level_id = (\n"
              + "    SELECT id FROM landmarks.administrative_level\n"
              + "    WHERE level_order = (\n"
              + "        SELECT l.level_order + 1 FROM landmarks.administrative_level l WHERE l.id = parent.level_id\n"
              + "    ) AND country = (\n"
              + "        SELECT country FROM landmarks.administrative_level l WHERE l.id = parent.level_id\n"
              + "    )\n"
              + ")\n"
              + "WHERE parent.id = :parentId\n"
              + "AND ST_Area(ST_Intersection(ST_MakeValid(child.geometry), ST_MakeValid(parent.geometry))) / ST_Area(ST_MakeValid(child.geometry)) > 0.98",
      nativeQuery = true)
  List<AdministrativeAreaEntity> findChildren(@Param("parentId") Integer parentId);
}
