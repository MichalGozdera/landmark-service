package eu.cokeman.cycleareastats.service.level;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.port.in.administrativelevel.CreateAdministrativeLevelUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.port.out.persistence.CountryRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;


public class CreateAdministrativeLevelService implements CreateAdministrativeLevelUseCase {

    private final AdministrativeLevelRepository levelRepository;
    private final LevelCountryBinder countryBinder;

    public CreateAdministrativeLevelService(AdministrativeLevelRepository levelRepository, CountryRepository countryRepository) {
        this.levelRepository = levelRepository;
        this.countryBinder = new LevelCountryBinder(countryRepository);
    }

    @Override
    public AdministrativeLevelId createLevel(AdministrativeLevel administrativeLevel) {
        administrativeLevel = countryBinder.bindCountryId(administrativeLevel);
        var x = this.levelRepository.createLevel(administrativeLevel);
        return x;
    }

}
