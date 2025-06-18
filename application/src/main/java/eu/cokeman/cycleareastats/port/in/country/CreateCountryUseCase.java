package eu.cokeman.cycleareastats.port.in.country;

import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.valueObject.CountryId;

public interface CreateCountryUseCase {
    CountryId create(Country country);
}

