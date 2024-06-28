package eu.cokeman.cycleareastats.service.landmark;

import eu.cokeman.cycleareastats.port.in.ImportLandmarksUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.LandmarksRepository;
import eu.cokeman.cycleareastats.valueObject.Country;
import eu.cokeman.cycleareastats.valueObject.LandmarkGeometryType;
import eu.cokeman.cycleareastats.valueObject.LandmarkName;


public class ImportLandmarksService implements ImportLandmarksUseCase {

    private final LandmarksRepository landmarksRepository;

    public ImportLandmarksService(LandmarksRepository landmarksRepository) {
        this.landmarksRepository = landmarksRepository;
    }

    @Override
    public void importLandmarks(String data[], Country country, LandmarkName name, LandmarkGeometryType type) {
        landmarksRepository.importLandmarks(data,country,name,type);
    }
}
