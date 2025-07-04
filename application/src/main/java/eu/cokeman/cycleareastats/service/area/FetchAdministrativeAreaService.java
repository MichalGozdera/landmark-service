package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.port.in.administrativearea.FetchAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;

public class FetchAdministrativeAreaService implements FetchAdministrativeAreaUseCase {
  private final AdministrativeAreaRepository administrativeAreaRepository;

  public FetchAdministrativeAreaService(AdministrativeAreaRepository administrativeAreaRepository) {
    this.administrativeAreaRepository = administrativeAreaRepository;
  }

  @Override
  public AdministrativeArea findArea(AdministrativeAreaId administrativeAreaId) {
    return administrativeAreaRepository.findByAdministrativeAreaId(administrativeAreaId);
  }

  @Override
  public AdministrativeArea findSimpleArea(AdministrativeAreaId administrativeAreaId) {
    return administrativeAreaRepository.findSimpleByAdministrativeAreaId(administrativeAreaId);
  }
}
