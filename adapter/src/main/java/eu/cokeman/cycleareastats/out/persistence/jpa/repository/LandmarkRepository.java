package eu.cokeman.cycleareastats.out.persistence.jpa.repository;

import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.mapper.LandmarkJpaMapper;
import eu.cokeman.cycleareastats.valueObject.Country;
import eu.cokeman.cycleareastats.valueObject.LandmarkGeometry;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;
import org.geotools.util.factory.GeoTools;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiLineString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class LandmarkRepository implements eu.cokeman.cycleareastats.port.out.persistence.LandmarkRepository {

    private static final Logger log = LoggerFactory.getLogger(LandmarkRepository.class);
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
    public List<Landmark> findChildren(LandmarkId landmarkId) {
        try {
            return springDataRepository
                    .findChildren(landmarkId.value()).stream()
                    .map(r -> mapper.mapJpaToInternal(r).build()).collect(Collectors.toList());
        }catch (Exception e){
            log.error("Error finding children of landmark with id {}", landmarkId);
        }
        return null;
    }

    @Override
    public Landmark updateLandmark(Landmark landmark) {
        return mapper.mapJpaToInternal(springDataRepository.saveAndFlush(mapper.mapToJpa(landmark))).build();
    }

    @Override
    public void deleteLandmark(LandmarkId landmarkId) {
        //todo
    }

    @Override
    public LandmarkId findParent(LandmarkId landmarkId) {
        try {
            var fetchedID = springDataRepository.findParent(landmarkId.value());
            if (fetchedID != null) {
                return new LandmarkId(fetchedID);
            }
        }catch (Exception e){
            log.error("Error finding parent of landmark with id {}", landmarkId);
        }
        return null;
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
    public LandmarkId importLandmark(Landmark landmark) {

            var jpaLandmark = mapper.mapToJpa(landmark);
            jpaLandmark.setid(landmark.getId().value());
            springDataRepository.save(jpaLandmark);

        return landmark.getId();
    }
}
