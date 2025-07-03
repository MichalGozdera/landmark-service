package eu.cokeman.cycleareastats.port.in.administrativelevel;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;

public interface FetchAdministrativeLevelUseCase {

  AdministrativeLevel findLevel(AdministrativeLevelId administrativeLevelId);
}
