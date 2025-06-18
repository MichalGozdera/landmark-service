package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.port.in.administrativearea.ConvertAdministrativeAreaGeometryUseCase;
import eu.cokeman.cycleareastats.port.in.administrativearea.ImportAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.port.out.publishing.AdministrativeAreaPublisher;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaGeometry;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;
import eu.cokeman.cycleareastats.valueObject.AreaName;

import java.io.Serializable;
import java.util.List;


public class ImportAdministrativeAreaService implements ImportAdministrativeAreaUseCase {

    private final AdministrativeAreaRepository areaRepository;
    private final AdministrativeLevelRepository levelRepository;
    private final AdministrativeAreaPublisher publisher;
    private final ConvertAdministrativeAreaGeometryUseCase converter;

    public ImportAdministrativeAreaService(AdministrativeAreaRepository areaRepository, AdministrativeLevelRepository levelRepository, AdministrativeAreaPublisher publisher, ConvertAdministrativeAreaGeometryUseCase converter) {
        this.areaRepository = areaRepository;
        this.levelRepository = levelRepository;
        this.publisher = publisher;
        this.converter = converter;
    }

    @Override
    public void processSubunit(AdministrativeAreaId administrativeAreaId) {
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
    }

    @Override
    public void importAdministrativeAreas(AdministrativeLevel level, Object geometry) {
        var geometriesConverted = converter.convertToLandmarksGeometries(geometry);
        var ids = geometriesConverted.stream().map(administrativeAreaGeometry -> importSingleArea(level, administrativeAreaGeometry)).toList();
        ids.stream().forEach(id -> publisher.publish(id));
    }

    private AdministrativeAreaId importSingleArea(AdministrativeLevel level, AdministrativeAreaGeometry geometryData) {
        var administrativeArea = AdministrativeArea.builder().level(level).build();
        administrativeArea = bindLevel(level, administrativeArea);
        administrativeArea = bindDataFromGeometry(geometryData, administrativeArea);
        return areaRepository.importLandmark(administrativeArea);
    }

    private AdministrativeArea bindDataFromGeometry(AdministrativeAreaGeometry geometryData, AdministrativeArea administrativeArea) {
        var geometriesSimplified = converter.getGeometriesSimplified(geometryData);
        if (geometriesSimplified.size() > 1) {
            System.out.println(geometryData.name());
        }
        administrativeArea = administrativeArea.toBuilder()
                .name(new AreaName(geometryData.name()))
                .geometry(geometryData.geometryData())
                .build();
        return administrativeArea;
    }

    private AdministrativeArea bindLevel(AdministrativeLevel level, AdministrativeArea administrativeArea) {
        var matchingLevelId = levelRepository.findByCountryAndName(level.getCountry(), level.getName()).orElseThrow().getId();
        administrativeArea = administrativeArea.toBuilder()
                .level(administrativeArea.getLevel().toBuilder().id(matchingLevelId).build()).build();
        return administrativeArea;
    }
}
