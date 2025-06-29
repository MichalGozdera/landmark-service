package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.events.AdministrativeAreaEvent;
import eu.cokeman.cycleareastats.port.in.administrativearea.DeleteAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.EntityEventType;

public class DeleteAdministrativeAreaService implements DeleteAdministrativeAreaUseCase {
    private final AdministrativeAreaDomainService administrativeAreaDomainService;

    public DeleteAdministrativeAreaService(AdministrativeAreaDomainService administrativeAreaDomainService) {
        this.administrativeAreaDomainService = administrativeAreaDomainService;
    }

    @Override
    public void deleteAdministrativeArea(AdministrativeAreaId administrativeAreaId) {
        administrativeAreaDomainService.deleteAdministrativeArea(administrativeAreaId);
        administrativeAreaDomainService.publishEvent(new AdministrativeAreaEvent(AdministrativeArea.builder().id(administrativeAreaId).build(), EntityEventType.DELETED));
    }
}
