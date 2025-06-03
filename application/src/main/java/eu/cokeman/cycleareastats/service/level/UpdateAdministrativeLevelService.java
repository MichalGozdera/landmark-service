package eu.cokeman.cycleareastats.service.level;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.port.in.administrativelevel.UpdateAdministrativeLevelUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;

public class UpdateAdministrativeLevelService implements UpdateAdministrativeLevelUseCase {
    private final AdministrativeLevelRepository levelRepository;

    public UpdateAdministrativeLevelService(AdministrativeLevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public AdministrativeLevel updateAdministrativeLevel(AdministrativeLevelId levelId, AdministrativeLevel level) {
        return levelRepository.updateLevel(levelId, level);
    }
}
