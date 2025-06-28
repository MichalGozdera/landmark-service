package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.events.AdministrativeAreaEvent;
import eu.cokeman.cycleareastats.port.in.administrativearea.ConvertAdministrativeAreaGeometryUseCase;
import eu.cokeman.cycleareastats.port.in.administrativearea.ImportAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.valueObject.*;


public class ImportAdministrativeAreaService implements ImportAdministrativeAreaUseCase {

    private final ConvertAdministrativeAreaGeometryUseCase converter;
    private final AdministrativeAreaDomainService domainService;

    public ImportAdministrativeAreaService(ConvertAdministrativeAreaGeometryUseCase converter, AdministrativeAreaDomainService domainService) {
        this.converter = converter;
        this.domainService = domainService;
    }


    @Override
    public void importAdministrativeAreas(AdministrativeLevel level, LandmarkMetadata metadata, Object geometry) {
        var geometriesConverted = converter.convertToLandmarksGeometries(geometry);
        var areas = geometriesConverted.stream().map(administrativeAreaGeometry -> importSingleArea(level, metadata, administrativeAreaGeometry)).toList();
        areas.forEach(area -> {
            area = area.toBuilder().geometry(domainService.getGeometriesSimplified(area)).build();
            var event = new AdministrativeAreaEvent(area, "create");
            domainService.publishEvent(event);
        });
    }

    private AdministrativeArea importSingleArea(AdministrativeLevel level, LandmarkMetadata metadata, AdministrativeAreaGeometry geometryData) {
        var administrativeArea = AdministrativeArea.builder().metadata(metadata).level(level).build();
        administrativeArea = domainService.bindLevelIfPresent(administrativeArea);
        administrativeArea = bindDataFromGeometry(geometryData, administrativeArea);
        AdministrativeAreaId id = domainService.saveArea(administrativeArea);
        administrativeArea=administrativeArea.toBuilder().id(id).build();
        return administrativeArea;
    }

    private AdministrativeArea bindDataFromGeometry(AdministrativeAreaGeometry geometryData, AdministrativeArea administrativeArea) {
        var geometriesSimplified = converter.getGeometriesSimplified(geometryData.geometryData());
        if (geometriesSimplified.encodedLines().size() > 1) {
            System.out.println(geometryData.name());
        }
        administrativeArea = administrativeArea.toBuilder()
                .name(new AreaName(geometryData.name()))
                .geometry(geometryData.geometryData())
                .build();
        return administrativeArea;
    }


}
