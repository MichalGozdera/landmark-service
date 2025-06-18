package eu.cokeman.cycleareastats.port.in.country;

import eu.cokeman.cycleareastats.valueObject.CountryId;

public interface DeleteCountryUseCase {
    void delete(CountryId id);
}

