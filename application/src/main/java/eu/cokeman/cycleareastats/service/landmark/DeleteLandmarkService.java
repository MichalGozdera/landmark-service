package eu.cokeman.cycleareastats.service.landmark;

import eu.cokeman.cycleareastats.port.in.DeleteLandmarkUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.LandmarkRepository;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;

public class DeleteLandmarkService implements DeleteLandmarkUseCase {
    private final LandmarkRepository landmarkRepository;

    public DeleteLandmarkService(LandmarkRepository landmarkRepository) {
        this.landmarkRepository = landmarkRepository;
    }

    @Override
    public void deleteLandmark(LandmarkId landmarkId) {
        landmarkRepository.deleteLandmark(landmarkId);
    }
}
