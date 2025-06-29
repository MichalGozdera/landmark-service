package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.events.AdministrativeAreaEvent;
import eu.cokeman.cycleareastats.exception.LevelNotFoundException;
import eu.cokeman.cycleareastats.port.in.administrativearea.PolylineEncoder;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.port.out.publishing.AdministrativeAreaPublisher;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaSimplifiedGeometry;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;
import eu.cokeman.cycleareastats.valueObject.EntityEventType;

import java.util.List;

public class AdministrativeAreaDomainService {
    private final AdministrativeAreaRepository areaRepository;
    private final AdministrativeAreaPublisher publisher;
    private final AdministrativeLevelRepository levelRepository;
    private final PolylineEncoder encoder;

    public AdministrativeAreaDomainService(AdministrativeAreaRepository areaRepository, AdministrativeLevelRepository levelRepository, AdministrativeAreaPublisher publisher, PolylineEncoder encoder) {
        this.areaRepository = areaRepository;
        this.publisher = publisher;
        this.levelRepository = levelRepository;
        this.encoder = encoder;
    }

    AdministrativeLevel findExistingLevel(AdministrativeArea administrativeArea) {
        var level = administrativeArea.getLevel();
        var matchingLevel = levelRepository.findByCountryAndName(level.getCountry(), level.getName())
                .orElseThrow(() -> new LevelNotFoundException(
                        String.format("Level with name %s and country %s not found", level.getCountry().getName(), level.getName().name())));
        return matchingLevel;
    }


    public void publishEvent(AdministrativeAreaEvent event) {
        publisher.publish(event);
    }

    public AdministrativeAreaId findParentId(AdministrativeAreaId areaId) {
        AdministrativeAreaId parentID = areaRepository.findParent(areaId);

        List<AdministrativeArea> children = areaRepository.findChildren(areaId);
        if (children != null && !children.isEmpty()) {
            for (AdministrativeArea child : children) {
                child = child.toBuilder().parent(areaId).build();
                persist(child, EntityEventType.UPDATED, child.getId());
            }
        }
        return parentID;
    }

    AdministrativeAreaSimplifiedGeometry getGeometriesSimplified(AdministrativeArea area) {
        return encoder.getGeometriesSimplified(area.getGeometry());
    }


    public AdministrativeAreaId saveArea(AdministrativeArea area) {
        return areaRepository.createArea(area);
    }

    private AdministrativeArea persist(AdministrativeArea area, EntityEventType eventType, AdministrativeAreaId idIfPresent) {
        AdministrativeLevel level = findExistingLevel(area);
        area = area.toBuilder().level(level).build();
        if (idIfPresent == null) {
            idIfPresent = saveArea(area);
        } else {
            areaRepository.updateAdministrativeArea(idIfPresent, area);
        }
        area = area.toBuilder().id(idIfPresent).build();

        AdministrativeAreaId parentId = findParentId(idIfPresent);
        if (area.getParent() == null && parentId != null) {
            area = area.toBuilder().parent(parentId).build();
            areaRepository.updateAdministrativeArea(idIfPresent, area);
        }
        AdministrativeAreaSimplifiedGeometry geometriesSimplified = null;
        if(area.getGeometry()!=null){
            geometriesSimplified = getGeometriesSimplified(area);

        }
        AdministrativeAreaEvent event = AdministrativeAreaEvent.builder()
                .area(area)
                .simplifiedGeometry(geometriesSimplified)
                .operationType(eventType)
                .build();
        publishEvent(event);
        return area;
    }

    public AdministrativeAreaId createAdministrativeArea(AdministrativeArea area) {
        AdministrativeArea processed = persist(area, EntityEventType.CREATED, null);
        return processed.getId();
    }

    public AdministrativeArea updateAdministrativeArea(AdministrativeAreaId areaId, AdministrativeArea area) {
        return persist(area, EntityEventType.UPDATED, areaId);
    }

    public void deleteAdministrativeArea(AdministrativeAreaId administrativeAreaId) {
        areaRepository.deleteAdministrativeArea(administrativeAreaId);
    }
}
