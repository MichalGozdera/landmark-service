package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.events.AdministrativeAreaEvent;
import eu.cokeman.cycleareastats.port.in.administrativearea.CreateAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaSimplifiedGeometry;
import eu.cokeman.cycleareastats.valueObject.EntityEventType;


public class CreateAdministrativeAreaService implements CreateAdministrativeAreaUseCase {


    private final AdministrativeAreaDomainService domainService;

    public CreateAdministrativeAreaService(AdministrativeAreaDomainService domainService) {
        this.domainService = domainService;
    }

    @Override
    public AdministrativeAreaId createAdministrativeArea(AdministrativeArea area) {
        area = domainService.bindLevelIfPresent(area);
        area = domainService.processSubunit(area);
        AdministrativeAreaSimplifiedGeometry geometriesSimplified = domainService.getGeometriesSimplified(area);
        AdministrativeAreaId id = domainService.saveArea(area);
        area = area.toBuilder().id(id).geometry(geometriesSimplified).build();
        AdministrativeAreaEvent event = new AdministrativeAreaEvent(area, EntityEventType.CREATED);
        domainService.publishEvent(event);
        return id;
    }


}
