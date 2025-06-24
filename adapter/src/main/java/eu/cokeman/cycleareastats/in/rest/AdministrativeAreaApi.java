package eu.cokeman.cycleareastats.in.rest;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.mapper.area.AdministrativeAreaExternalMapper;
import eu.cokeman.cycleareastats.mapper.level.AdministrativeLevelExternalMapper;
import eu.cokeman.cycleareastats.openapi.model.*;
import eu.cokeman.cycleareastats.port.in.administrativearea.DeleteAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.in.administrativearea.FetchAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.in.administrativearea.FilterAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.in.administrativearea.ImportAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.in.administrativearea.UpdateAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.LandmarkMetadata;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


@RestController
public class AdministrativeAreaApi implements eu.cokeman.cycleareastats.openapi.api.AdministrativeAreaApi {

    private final FetchAdministrativeAreaUseCase fetchAdministrativeAreaUseCase;
    private final UpdateAdministrativeAreaUseCase updateAdministrativeAreaUseCase;
    private final DeleteAdministrativeAreaUseCase deleteAdministrativeAreaUseCase;
    private final FilterAdministrativeAreaUseCase filterAdministrativeAreaUseCase;

    AdministrativeAreaExternalMapper areaMapper = AdministrativeAreaExternalMapper.INSTANCE;

    public AdministrativeAreaApi(
            FetchAdministrativeAreaUseCase fetchAdministrativeAreaUseCase,
            UpdateAdministrativeAreaUseCase updateAdministrativeAreaUseCase,
            DeleteAdministrativeAreaUseCase deleteAdministrativeAreaUseCase,
            FilterAdministrativeAreaUseCase filterAdministrativeAreaUseCase) {
        this.fetchAdministrativeAreaUseCase = fetchAdministrativeAreaUseCase;
        this.updateAdministrativeAreaUseCase = updateAdministrativeAreaUseCase;
        this.deleteAdministrativeAreaUseCase = deleteAdministrativeAreaUseCase;
        this.filterAdministrativeAreaUseCase = filterAdministrativeAreaUseCase;

    }


    @Override
    public ResponseEntity<AdministrativeAreaDto> loadAdministrativeArea(Integer areaIdExternal) {
        AdministrativeAreaId areaInternalId = areaMapper.mapToAdmAreaId(areaIdExternal);
        AdministrativeArea administrativeArea = fetchAdministrativeAreaUseCase.findArea(areaInternalId);
        AdministrativeAreaDto areaResponse = areaMapper.mapToExternal(administrativeArea);
        return ResponseEntity.ok(areaResponse);
    }

    @Override
    public ResponseEntity<AdministrativeAreaDto> updateAdministrativeArea(Integer administrativeAreaId, AdministrativeAreaDto administrativeAreaDto) {
        AdministrativeAreaId areaInternalId = areaMapper.mapToAdmAreaId(administrativeAreaId);
        AdministrativeArea area = areaMapper.mapToInternal(administrativeAreaDto).build();
        AdministrativeArea updatedArea = updateAdministrativeAreaUseCase.updateAdministrativeArea(areaInternalId, area);
        var updatedAreaDto = areaMapper.mapToExternal(updatedArea);
        return ResponseEntity.ok(updatedAreaDto);
    }

    @Override
    public ResponseEntity<Void> deleteAdministrativeArea(Integer administrativeAreaId) {
        AdministrativeAreaId areaInternalId = areaMapper.mapToAdmAreaId(administrativeAreaId);
        deleteAdministrativeAreaUseCase.deleteAdministrativeArea(areaInternalId);
        return ResponseEntity.ok().build();
    }


    @Override
    public ResponseEntity<List<AdministrativeAreaDto>> getAdministrativeAreasByLevelAndCountry(String levelName, String countryName) {
        var areas = filterAdministrativeAreaUseCase.findByLevelAndCountry(levelName, countryName);
        var dtos = areas.stream().map(areaMapper::mapToExternal).toList();
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<List<AdministrativeAreaDto>> getAdministrativeAreasByMetadata(String metadataQuery) {
        var areas = filterAdministrativeAreaUseCase.findByMetadataContains(metadataQuery);
        var dtos = areas.stream().map(areaMapper::mapToExternal).toList();
        return ResponseEntity.ok(dtos);
    }


}
