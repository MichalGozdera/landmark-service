package eu.cokeman.cycleareastats.service.level;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.port.in.administrativelevel.UpdateAdministrativeLevelUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.port.out.persistence.CountryRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;

public class UpdateAdministrativeLevelService implements UpdateAdministrativeLevelUseCase {
    private final AdministrativeLevelRepository levelRepository;
    private final LevelCountryBinder countryBinder;


    public UpdateAdministrativeLevelService(AdministrativeLevelRepository levelRepository, CountryRepository countryRepository) {
        this.levelRepository = levelRepository;
        this.countryBinder = new LevelCountryBinder(countryRepository);
    }

    @Override
    public AdministrativeLevel updateAdministrativeLevel(AdministrativeLevelId levelId, AdministrativeLevel level) {
        level = this.countryBinder.bindCountryId(level);
        return levelRepository.updateLevel(levelId, level);
    }
}
