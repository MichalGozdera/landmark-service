package eu.cokeman.cycleareastats.in.rest;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.mapper.area.AdministrativeAreaExternalMapper;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeAreaRequestDto;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeAreaResponseDto;
import eu.cokeman.cycleareastats.port.in.administrativearea.DeleteAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.in.administrativearea.FetchAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.in.administrativearea.FilterAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.in.administrativearea.UpdateAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
    public ResponseEntity<AdministrativeAreaResponseDto> loadAdministrativeArea(Integer areaIdExternal) {
        AdministrativeAreaId areaInternalId = areaMapper.mapToAdmAreaId(areaIdExternal);
        AdministrativeArea administrativeArea = fetchAdministrativeAreaUseCase.findArea(areaInternalId);
        AdministrativeAreaResponseDto areaResponse = areaMapper.mapToExternal(administrativeArea);
        return ResponseEntity.ok(areaResponse);
    }

    @Override
    public ResponseEntity<AdministrativeAreaResponseDto> updateAdministrativeArea(Integer administrativeAreaId, AdministrativeAreaRequestDto administrativeAreaDto) {
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
    public ResponseEntity<List<AdministrativeAreaResponseDto>> getAdministrativeAreasByLevelAndCountry(String levelName, String countryName) {
        var areas = filterAdministrativeAreaUseCase.findByLevelAndCountry(levelName, countryName);
        var dtos = areas.stream().map(areaMapper::mapToExternal).toList();
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<List<AdministrativeAreaResponseDto>> getAdministrativeAreasByMetadata(String metadataQuery) {
        var areas = filterAdministrativeAreaUseCase.findByMetadataContains(metadataQuery);
        var dtos = areas.stream().map(areaMapper::mapToExternal).toList();
        return ResponseEntity.ok(dtos);
    }


}
