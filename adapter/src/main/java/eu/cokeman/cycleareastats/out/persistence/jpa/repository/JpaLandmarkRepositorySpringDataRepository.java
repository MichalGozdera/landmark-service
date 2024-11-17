package eu.cokeman.cycleareastats.out.persistence.jpa.repository;

import eu.cokeman.cycleareastats.out.persistence.jpa.entity.LandmarkEntity;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaLandmarkRepositorySpringDataRepository extends JpaRepository<LandmarkEntity, UUID> {

    Optional<LandmarkEntity> findById(UUID landmarkID);

    @Query(value = "SELECT x2.* FROM (SELECT x.landmarkid, x.name, st_area(ST_Intersection((SELECT st_makevalid(ST_BuildArea(x1.geometry)) \n" +
            "FROM landmark x1 WHERE x1.landmarkid=:landmarkID), st_makevalid(ST_BuildArea(x.geometry))))/st_area(st_makevalid(ST_BuildArea(x.geometry)))\n" +
            " AS coverage FROM landmark x WHERE x.landmarkid !=:landmarkID AND x.category='ADMINISTRATIVE_REGION' AND x.geometrytype='POLYGON' ) calcs JOIN landmark x2 on x2.landmarkid=calcs.landmarkid\n" +
            " WHERE  coverage >0.8 AND coverage <1.2", nativeQuery = true)
    List<LandmarkEntity> findChildren(@Param("landmarkID") UUID landmarkID);



    @Query(value = "SELECT  x2.landmarkid FROM (SELECT x.landmarkid, x.name,\n" +
            "st_area(st_makevalid(ST_BuildArea(x.geometry))) AS AREA, \n" +
            "st_area\n" +
            "(ST_Intersection\n" +
            "(\n" +
            "(SELECT st_makevalid(ST_BuildArea(x1.geometry)) \n" +
            "FROM landmark x1 WHERE x1.landmarkid=:landmarkID)\n" +
            ", st_makevalid(ST_BuildArea(x.geometry))\n" +
            ")\n" +
            ")\n" +
            "/(select st_area(st_makevalid(ST_BuildArea(x1.geometry))) \n" +
            "FROM landmark x1 WHERE x1.landmarkid=:landmarkID)\n" +
            " AS coverage FROM landmark x WHERE x.landmarkid!=:landmarkID and x.category='ADMINISTRATIVE_REGION' AND x.geometrytype='POLYGON' ) calcs JOIN landmark x2 on x2.landmarkid=calcs.landmarkid\n" +
            "WHERE  coverage >0.5 AND coverage <1.2 ORDER BY calcs.area LIMIT 1", nativeQuery = true)
    UUID findParent(@Param("landmarkID") UUID landmarkID);

}