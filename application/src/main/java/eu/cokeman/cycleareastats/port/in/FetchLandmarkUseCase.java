package eu.cokeman.cycleareastats.port.in;


import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;


public interface FetchLandmarkUseCase {

    Landmark findLandmark(LandmarkId landmarkId);
}
