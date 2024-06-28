package eu.cokeman.cycleareastats.service.landmark;

import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.port.in.FilterLandmarkUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.LandmarkRepository;
import eu.cokeman.cycleareastats.valueObject.Country;

import java.util.List;

public class FilterLandmarkService implements FilterLandmarkUseCase {

    private final LandmarkRepository landmarkRepository;

    public FilterLandmarkService(LandmarkRepository landmarkRepository) {
        this.landmarkRepository = landmarkRepository;
    }

    @Override
    public List<Landmark> filterByCountry(Country country) {
      return landmarkRepository.findByCountry(country);
    }
}
