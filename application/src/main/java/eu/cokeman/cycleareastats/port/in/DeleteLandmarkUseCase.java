package eu.cokeman.cycleareastats.port.in;

import eu.cokeman.cycleareastats.valueObject.LandmarkId;

public interface DeleteLandmarkUseCase {
    void deleteLandmark(LandmarkId landmarkId);
}
