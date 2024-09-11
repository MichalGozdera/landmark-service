package eu.cokeman.cycleareastats.out.persistence.jpa.repository;

import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.mapper.LandmarkJpaMapper;
import eu.cokeman.cycleareastats.valueObject.Country;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiLineString;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class LandmarkRepository implements eu.cokeman.cycleareastats.port.out.persistence.LandmarkRepository {

    LandmarkJpaMapper mapper = LandmarkJpaMapper.INSTANCE;

    private final JpaLandmarkRepositorySpringDataRepository springDataRepository;

    public LandmarkRepository(JpaLandmarkRepositorySpringDataRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Landmark findByLandmarkId(LandmarkId landmarkId) {
        var jpaLandmark =  springDataRepository.findById(landmarkId.value());
        Landmark landmark = mapper.mapJpaToInternal(jpaLandmark.orElseThrow()).build();
        return landmark;
    }

    @Override
    public Landmark updateLandmark(LandmarkId landmarkId, Landmark landmark) {
        return null;
    }

    @Override
    public void deleteLandmark(LandmarkId landmarkId) {

    }

    @Override
    public List<Landmark> findByCountry(Country country) {
        return List.of();
    }

    @Override
    public List<Landmark> filterLandMarks(String criteria) {
        return List.of();
    }

    @Override
    public LandmarkId importLandmark(Landmark landmark, Object geometry) {
        List<Geometry> geometries = (List<Geometry>) geometry;
        for(Geometry geometry1:geometries){
            var jpaLandmark = mapper.mapToJpa(landmark);
            jpaLandmark.setid(UUID.randomUUID());
            jpaLandmark.setGeom(geometry1);
            springDataRepository.save(jpaLandmark);
        }
        return landmark.getId();
    }
}
