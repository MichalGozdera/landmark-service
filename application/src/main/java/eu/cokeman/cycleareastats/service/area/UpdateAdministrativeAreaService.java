package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.port.in.administrativearea.UpdateAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;

public class UpdateAdministrativeAreaService implements UpdateAdministrativeAreaUseCase {
  private final AdministrativeAreaDomainService administrativeAreaDomainService;

  public UpdateAdministrativeAreaService(
      AdministrativeAreaDomainService administrativeAreaDomainService) {
    this.administrativeAreaDomainService = administrativeAreaDomainService;
  }

  @Override
  public AdministrativeArea updateAdministrativeArea(
      AdministrativeAreaId areaId, AdministrativeArea administrativeArea) {
    return administrativeAreaDomainService.updateAdministrativeArea(areaId, administrativeArea);
  }
}
