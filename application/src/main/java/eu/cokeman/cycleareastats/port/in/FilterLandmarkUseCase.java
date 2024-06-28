package eu.cokeman.cycleareastats.port.in;

import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.valueObject.Country;

import java.util.List;

public interface FilterLandmarkUseCase {

    List<Landmark> filterByCountry(Country country);
}
