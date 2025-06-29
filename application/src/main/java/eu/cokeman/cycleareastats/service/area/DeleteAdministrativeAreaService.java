package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.port.in.administrativearea.DeleteAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;

public class DeleteAdministrativeAreaService implements DeleteAdministrativeAreaUseCase {
    private final AdministrativeAreaDomainService administrativeAreaDomainService;

    public DeleteAdministrativeAreaService(AdministrativeAreaDomainService administrativeAreaDomainService) {
        this.administrativeAreaDomainService = administrativeAreaDomainService;
    }

    @Override
    public void deleteAdministrativeArea(AdministrativeAreaId administrativeAreaId) {
        administrativeAreaDomainService.deleteAdministrativeArea(administrativeAreaId);
    }
}
