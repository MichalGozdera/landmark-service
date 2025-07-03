package eu.cokeman.cycleareastats.port.in.administrativearea;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;

public interface UpdateAdministrativeAreaUseCase {
  AdministrativeArea updateAdministrativeArea(
      AdministrativeAreaId areaId, AdministrativeArea administrativeArea);
}
