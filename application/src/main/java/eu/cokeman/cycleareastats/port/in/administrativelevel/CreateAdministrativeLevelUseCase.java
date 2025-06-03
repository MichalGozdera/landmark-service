package eu.cokeman.cycleareastats.port.in.administrativelevel;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;


public interface CreateAdministrativeLevelUseCase {

    void createLevel(AdministrativeLevel administrativeLevel);

}
