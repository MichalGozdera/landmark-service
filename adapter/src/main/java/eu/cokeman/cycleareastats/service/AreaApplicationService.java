package eu.cokeman.cycleareastats.service;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.mapper.area.AdministrativeAreaExternalMapper;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeAreaRequestDto;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeAreaResponseDto;
import eu.cokeman.cycleareastats.openapi.model.CreateAdministrativeAreaRequestDto;
import eu.cokeman.cycleareastats.port.in.administrativearea.*;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;

public class AreaApplicationService {

    private final FetchAdministrativeAreaUseCase fetchAdministrativeAreaUseCase;
    private final UpdateAdministrativeAreaUseCase updateAdministrativeAreaUseCase;
    private final DeleteAdministrativeAreaUseCase deleteAdministrativeAreaUseCase;
    private final FilterAdministrativeAreaUseCase filterAdministrativeAreaUseCase;
    private final CreateAdministrativeAreaUseCase createAdministrativeAreaUseCase;

    AdministrativeAreaExternalMapper areaMapper = AdministrativeAreaExternalMapper.INSTANCE;

    public AreaApplicationService(FetchAdministrativeAreaUseCase fetchAdministrativeAreaUseCase, UpdateAdministrativeAreaUseCase updateAdministrativeAreaUseCase, DeleteAdministrativeAreaUseCase deleteAdministrativeAreaUseCase, FilterAdministrativeAreaUseCase filterAdministrativeAreaUseCase, CreateAdministrativeAreaUseCase createAdministrativeAreaUseCase) {
        this.fetchAdministrativeAreaUseCase = fetchAdministrativeAreaUseCase;
        this.updateAdministrativeAreaUseCase = updateAdministrativeAreaUseCase;
        this.deleteAdministrativeAreaUseCase = deleteAdministrativeAreaUseCase;
        this.filterAdministrativeAreaUseCase = filterAdministrativeAreaUseCase;
        this.createAdministrativeAreaUseCase = createAdministrativeAreaUseCase;
    }

    @Transactional
    public AdministrativeAreaId createAdministrativeArea(CreateAdministrativeAreaRequestDto createAdministrativeAreaRequestDto) {
        AdministrativeArea area = areaMapper.mapToInternal(createAdministrativeAreaRequestDto.getRequest()).build();
        return createAdministrativeAreaUseCase.createAdministrativeArea(area);

    }

    @Transactional
    public void deleteAdministrativeArea(Integer administrativeAreaId) {
        AdministrativeAreaId areaInternalId = areaMapper.mapToAdmAreaId(administrativeAreaId);
        deleteAdministrativeAreaUseCase.deleteAdministrativeArea(areaInternalId);
    }

    public AdministrativeAreaResponseDto loadAdministrativeArea(Integer areaIdExternal) {
        AdministrativeAreaId areaInternalId = areaMapper.mapToAdmAreaId(areaIdExternal);
        AdministrativeArea administrativeArea = fetchAdministrativeAreaUseCase.findArea(areaInternalId);
        return areaMapper.mapToExternal(administrativeArea);
    }

    @Transactional
    public AdministrativeAreaResponseDto updateAdministrativeArea(Integer administrativeAreaId, AdministrativeAreaRequestDto administrativeAreaDto) {
        AdministrativeAreaId areaInternalId = areaMapper.mapToAdmAreaId(administrativeAreaId);
        AdministrativeArea area = areaMapper.mapToInternal(administrativeAreaDto).build();
        AdministrativeArea updatedArea = updateAdministrativeAreaUseCase.updateAdministrativeArea(areaInternalId, area);
        return areaMapper.mapToExternal(updatedArea);
    }

    public List<AdministrativeAreaResponseDto> getAdministrativeAreasByLevelAndCountry(String levelName, String countryName) {
        var areas = filterAdministrativeAreaUseCase.findByLevelAndCountry(levelName, countryName);
        return areas.stream().map(areaMapper::mapToExternal).toList();
    }

    public List<AdministrativeAreaResponseDto> getAdministrativeAreasByMetadata(String metadataQuery) {
        var areas = filterAdministrativeAreaUseCase.findByMetadataContains(metadataQuery);
        return areas.stream().map(areaMapper::mapToExternal).toList();
    }
}
