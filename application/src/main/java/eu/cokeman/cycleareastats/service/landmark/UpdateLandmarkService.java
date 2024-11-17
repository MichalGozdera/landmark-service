package eu.cokeman.cycleareastats.service.landmark;

import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.port.in.UpdateLandmarkUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.LandmarkRepository;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;

public class UpdateLandmarkService implements UpdateLandmarkUseCase {
    private final LandmarkRepository landmarkRepository;

    public UpdateLandmarkService(LandmarkRepository landmarkRepository) {
        this.landmarkRepository = landmarkRepository;
    }

    @Override
    public Landmark updateLandmark(Landmark landmark) {
       return landmarkRepository.updateLandmark(landmark);
    }
}
