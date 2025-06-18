package eu.cokeman.cycleareastats.service.country;

import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.port.in.country.CreateCountryUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.CountryRepository;
import eu.cokeman.cycleareastats.valueObject.CountryId;

public class CreateCountryService implements CreateCountryUseCase {
    private final CountryRepository countryRepository;

    public CreateCountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public CountryId create(Country country) {
        return countryRepository.create(country);
    }
}

