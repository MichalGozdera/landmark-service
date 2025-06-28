package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.events.AdministrativeAreaEvent;
import eu.cokeman.cycleareastats.port.in.administrativearea.ConvertAdministrativeAreaGeometryUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.port.out.publishing.AdministrativeAreaPublisher;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaSimplifiedGeometry;

import java.util.List;

public class AdministrativeAreaDomainService {
    private final AdministrativeAreaRepository areaRepository;
    private final AdministrativeAreaPublisher publisher;
    private final AreaLevelBinder levelBinder;
    private final ConvertAdministrativeAreaGeometryUseCase converter;

    public AdministrativeAreaDomainService(AdministrativeAreaRepository areaRepository, AdministrativeLevelRepository levelRepository, AdministrativeAreaPublisher publisher, ConvertAdministrativeAreaGeometryUseCase converter) {
        this.areaRepository = areaRepository;
        this.publisher = publisher;
        this.levelBinder = new AreaLevelBinder(levelRepository);
        this.converter = converter;
    }

    public AdministrativeArea bindLevelIfPresent(AdministrativeArea area) {
        if (area.getLevel() != null) {
            return levelBinder.bindLevelData(area);
        }
        return area;
    }

    public AdministrativeAreaId saveArea(AdministrativeArea area) {
        return areaRepository.createArea(area);
    }


    public void publishEvent(AdministrativeAreaEvent event) {
        publisher.publish(event);
    }

    public AdministrativeArea processSubunit(AdministrativeArea area) {
//        AdministrativeArea administrativeArea = areaRepository.findByAdministrativeAreaId(administrativeAreaId);
//        AdministrativeAreaId parentID = areaRepository.findParent(administrativeAreaId);
//        administrativeArea = administrativeArea.toBuilder().parent(parentID).build();
//        areaRepository.updateAdministrativeArea(administrativeArea);
//
//        List<AdministrativeArea> children = areaRepository.findSubUnits(administrativeAreaId);
//        if (children != null && !children.isEmpty()) {
//            for (AdministrativeArea child : children) {
//                child = administrativeArea.toBuilder().parent(administrativeAreaId).build();
//                areaRepository.updateAdministrativeArea(child);
//            }
//        }
        return area;
    }

    AdministrativeAreaSimplifiedGeometry getGeometriesSimplified(AdministrativeArea area) {
        return converter.getGeometriesSimplified(area.getGeometry());
    }

    // Możesz dodać tu kolejne wspólne metody
}
