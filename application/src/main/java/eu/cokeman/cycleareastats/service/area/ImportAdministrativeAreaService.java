package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.port.in.administrativearea.AdministrativeAreaConverter;
import eu.cokeman.cycleareastats.port.in.administrativearea.ImportAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.valueObject.*;


public class ImportAdministrativeAreaService implements ImportAdministrativeAreaUseCase {

    private final AdministrativeAreaConverter converter;
    private final AdministrativeAreaDomainService domainService;

    public ImportAdministrativeAreaService(AdministrativeAreaConverter converter, AdministrativeAreaDomainService domainService) {
        this.converter = converter;
        this.domainService = domainService;
    }


    @Override
    public void importAdministrativeAreas(AdministrativeLevel level, LandmarkMetadata metadata, Object geometry) {
        var geometriesConverted = converter.convertToLandmarksGeometries(geometry);
        geometriesConverted.forEach(administrativeAreaGeometry -> importSingleArea(level, metadata, administrativeAreaGeometry));

    }

    private void importSingleArea(AdministrativeLevel level, LandmarkMetadata metadata, AdministrativeAreaGeometry geometryData) {
        var administrativeArea = AdministrativeArea.builder()
                .name(new AreaName(geometryData.name()))
                .geometry(geometryData.geometryData())
                .metadata(metadata)
                .level(level)
                .build();
        domainService.createAdministrativeArea(administrativeArea);
    }


}
