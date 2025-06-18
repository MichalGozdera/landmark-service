package eu.cokeman.cycleareastats.service.level;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.port.in.administrativelevel.CreateAdministrativeLevelUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.port.out.persistence.CountryRepository;


public class CreateAdministrativeLevelService implements CreateAdministrativeLevelUseCase {

    private final AdministrativeLevelRepository levelRepository;
    private final CountryRepository countryRepository;

    public CreateAdministrativeLevelService(AdministrativeLevelRepository levelRepository, CountryRepository countryRepository) {
        this.levelRepository = levelRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public void createLevel(AdministrativeLevel administrativeLevel) {
        administrativeLevel = bindCountry(administrativeLevel);
        this.levelRepository.createLevel(administrativeLevel);
    }

    private AdministrativeLevel bindCountry(AdministrativeLevel level) {
        var matchingCountryId = countryRepository.findByName(level.getCountry().getName()).orElseThrow().getId();
        AdministrativeLevel updatedLevel = level.toBuilder()
                .country(level.getCountry().toBuilder().id(matchingCountryId).build()).build();
        return updatedLevel;
    }
}
