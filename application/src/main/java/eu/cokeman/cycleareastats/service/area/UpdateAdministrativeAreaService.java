package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.port.in.administrativearea.UpdateAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;

public class UpdateAdministrativeAreaService implements UpdateAdministrativeAreaUseCase {
    private final AdministrativeAreaRepository administrativeAreaRepository;

    public UpdateAdministrativeAreaService(AdministrativeAreaRepository administrativeAreaRepository) {
        this.administrativeAreaRepository = administrativeAreaRepository;
    }

    @Override
    public AdministrativeArea updateAdministrativeArea(AdministrativeAreaId areaId, AdministrativeArea administrativeArea) {
       return administrativeAreaRepository.updateAdministrativeArea(areaId, administrativeArea);
    }
}
