package eu.cokeman.cycleareastats.port.in.administrativelevel;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;

public interface CreateAdministrativeLevelUseCase {

  AdministrativeLevelId createLevel(AdministrativeLevel administrativeLevel);
}
