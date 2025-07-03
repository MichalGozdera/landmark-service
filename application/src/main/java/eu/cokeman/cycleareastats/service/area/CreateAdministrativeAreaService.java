package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.port.in.administrativearea.CreateAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;

public class CreateAdministrativeAreaService implements CreateAdministrativeAreaUseCase {

  private final AdministrativeAreaDomainService domainService;

  public CreateAdministrativeAreaService(AdministrativeAreaDomainService domainService) {
    this.domainService = domainService;
  }

  @Override
  public AdministrativeAreaId createAdministrativeArea(AdministrativeArea area) {
    return domainService.createAdministrativeArea(area);
  }
}
