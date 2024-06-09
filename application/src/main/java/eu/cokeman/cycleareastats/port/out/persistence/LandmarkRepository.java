package eu.cokeman.cycleareastats.port.out.persistence;

import eu.cokeman.cycleareastats.Landmark;
import eu.cokeman.cycleareastats.LandmarkID;

import java.util.Optional;

public interface LandmarkRepository {
    void save(Landmark landmark);

    Optional<Landmark> findByLandmarkId(LandmarkID landmarkID);
    void updateByLandmarkId(LandmarkID landmarkID, Landmark landmark);

    void deleteByLandmarkId(LandmarkID landmarkID);
}
