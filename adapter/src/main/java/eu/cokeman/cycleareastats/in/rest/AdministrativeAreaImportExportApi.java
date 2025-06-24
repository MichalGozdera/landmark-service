package eu.cokeman.cycleareastats.in.rest;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.mapper.area.AdministrativeAreaExternalMapper;
import eu.cokeman.cycleareastats.mapper.level.AdministrativeLevelExternalMapper;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeAreasImportRequestDto;
import eu.cokeman.cycleareastats.port.in.administrativearea.ExportAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.in.administrativearea.ImportAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.valueObject.LandmarkMetadata;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AdministrativeAreaImportExportApi implements eu.cokeman.cycleareastats.openapi.api.AdministrativeAreaImportExportApi {
    private final ImportAdministrativeAreaUseCase importAdministrativeAreaUseCase;
    private final ExportAdministrativeAreaUseCase exportAdministrativeAreaUseCase;

    AdministrativeAreaExternalMapper areaMapper = AdministrativeAreaExternalMapper.INSTANCE;
    AdministrativeLevelExternalMapper levelMapper = AdministrativeLevelExternalMapper.INSTANCE;

    public AdministrativeAreaImportExportApi(ImportAdministrativeAreaUseCase importAdministrativeAreaUseCase, ExportAdministrativeAreaUseCase exportAdministrativeAreaUseCase) {
        this.importAdministrativeAreaUseCase = importAdministrativeAreaUseCase;
        this.exportAdministrativeAreaUseCase = exportAdministrativeAreaUseCase;
    }


    @Override
    public ResponseEntity<Resource> exportAdministrativeAreasKml(String countryName, String levelName, String fileName) {
        String kml = exportAdministrativeAreaUseCase.exportKmlByCountryAndLevel(countryName, levelName);
        Resource resource = new org.springframework.core.io.ByteArrayResource(kml.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + fileName + ".kml")
                .body(resource);
    }

    @Override
    public ResponseEntity<Void> importAdministrativeAreas(AdministrativeAreasImportRequestDto requestDto, MultipartFile geometry) {
        // Validate KML file by extension only
        String filename = geometry.getOriginalFilename();
        if (filename == null || !filename.toLowerCase().endsWith(".kml")) {
            return ResponseEntity.badRequest().header("X-Error-Message", "File must have .kml extension").build();
        }
        AdministrativeLevel level = levelMapper.mapToInternal(requestDto.getLevel()).build();
        LandmarkMetadata metadata = areaMapper.mapJsonToLandmarkMetadata(requestDto.getMetadata());
        importAdministrativeAreaUseCase.importAdministrativeAreas(level, metadata, geometry);
        return ResponseEntity.ok().build();
    }
}
