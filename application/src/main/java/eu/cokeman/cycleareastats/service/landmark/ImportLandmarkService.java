package eu.cokeman.cycleareastats.service.landmark;

import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.port.in.ImportLandmarkUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.LandmarkRepository;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;



public class ImportLandmarkService implements ImportLandmarkUseCase {

    private final LandmarkRepository landmarkRepository;

    public ImportLandmarkService(LandmarkRepository landmarkRepository) {
        this.landmarkRepository = landmarkRepository;
    }

    @Override
    public LandmarkId importLandmark(Landmark landmark) {
        landmark.initLandmark();
       return landmarkRepository.importLandmark(landmark);
    }
}
