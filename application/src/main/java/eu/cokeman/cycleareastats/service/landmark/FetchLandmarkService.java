package eu.cokeman.cycleareastats.service.landmark;

import eu.cokeman.cycleareastats.Landmark;
import eu.cokeman.cycleareastats.LandmarkID;
import eu.cokeman.cycleareastats.port.in.FetchLandmarkUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.LandmarkRepository;

public class FetchLandmarkService implements FetchLandmarkUseCase {
    private final LandmarkRepository landmarkRepository;

    public FetchLandmarkService(LandmarkRepository landmarkRepository) {
        this.landmarkRepository = landmarkRepository;
    }

    @Override
    public Landmark getLandmark(LandmarkID landmarkID) {
        return landmarkRepository.findByLandmarkId(landmarkID).orElseThrow();
    }
}
