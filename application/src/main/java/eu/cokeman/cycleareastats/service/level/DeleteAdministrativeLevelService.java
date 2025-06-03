package eu.cokeman.cycleareastats.service.level;

import eu.cokeman.cycleareastats.port.in.administrativelevel.DeleteAdministrativeLevelUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;

public class DeleteAdministrativeLevelService implements DeleteAdministrativeLevelUseCase {
    private final AdministrativeLevelRepository levelRepository;

    public DeleteAdministrativeLevelService(AdministrativeLevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public void deleteAdministrativeLevel(AdministrativeLevelId levelId) {
        levelRepository.deleteLevel(levelId);
    }
}
