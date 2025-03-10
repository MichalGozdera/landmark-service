package eu.cokeman.cycleareastats.service.landmark;


import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.port.in.FetchLandmarkUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.LandmarkRepository;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;

public class FetchLandmarkService implements FetchLandmarkUseCase {
    private final LandmarkRepository landmarkRepository;

    public FetchLandmarkService(LandmarkRepository landmarkRepository) {
        this.landmarkRepository = landmarkRepository;
    }

    @Override
    public Landmark findLandmark(LandmarkId landmarkId) {
        return landmarkRepository.findByLandmarkId(landmarkId);
    }
}
