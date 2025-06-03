package eu.cokeman.cycleareastats.port.in.administrativelevel;

import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;

public interface DeleteAdministrativeLevelUseCase {

    void deleteAdministrativeLevel(AdministrativeLevelId levelId);
}
