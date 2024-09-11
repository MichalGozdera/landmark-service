package eu.cokeman.cycleareastats.port.out.persistence;


import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.valueObject.Country;

import eu.cokeman.cycleareastats.valueObject.LandmarkId;

import java.util.List;
import java.util.Optional;

public interface LandmarkRepository {

    Landmark findByLandmarkId(LandmarkId landmarkId);

    Landmark updateLandmark(LandmarkId landmarkId, Landmark landmark);

    void deleteLandmark(LandmarkId landmarkId);

    List<Landmark> findByCountry(Country country);

    List<Landmark> filterLandMarks(String criteria);

    LandmarkId importLandmark(Landmark landmark, Object geometry);


}
