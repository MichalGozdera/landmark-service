package eu.cokeman.cycleareastats.service.level;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.exception.CountryNotFoundException;
import eu.cokeman.cycleareastats.port.out.persistence.CountryRepository;

public class LevelCountryBinder {

    private final CountryRepository countryRepository;


    public LevelCountryBinder(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    AdministrativeLevel bindCountryId(AdministrativeLevel level) {
        var matchingCountryId = countryRepository.findByName(level.getCountry().getName())
                .orElseThrow(() -> new CountryNotFoundException(
                        String.format("Country with name %s not found", level.getCountry().getName()))).getId();
        AdministrativeLevel updatedLevel = level.toBuilder()
                .country(level.getCountry().toBuilder().id(matchingCountryId).build()).build();
        return updatedLevel;
    }
}
