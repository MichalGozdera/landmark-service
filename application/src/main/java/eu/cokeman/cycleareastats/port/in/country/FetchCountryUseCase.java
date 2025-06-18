package eu.cokeman.cycleareastats.port.in.country;

import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.valueObject.CountryId;
import java.util.List;

public interface FetchCountryUseCase {
    Country findById(CountryId id);
    List<Country> findAll();
}

