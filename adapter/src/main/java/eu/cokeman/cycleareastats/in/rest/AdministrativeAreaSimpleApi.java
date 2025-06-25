package eu.cokeman.cycleareastats.in.rest;

import eu.cokeman.cycleareastats.mapper.area.AdministrativeAreaExternalMapper;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeAreaResponseDto;
import eu.cokeman.cycleareastats.port.in.administrativearea.FetchAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.in.administrativearea.FilterAdministrativeAreaUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdministrativeAreaSimpleApi implements eu.cokeman.cycleareastats.openapi.api.AdministrativeAreaSimpleApi {
    private final FetchAdministrativeAreaUseCase fetchAdministrativeAreaUseCase;
    private final FilterAdministrativeAreaUseCase filterAdministrativeAreaUseCase;

    AdministrativeAreaExternalMapper areaMapper = AdministrativeAreaExternalMapper.INSTANCE;

    public AdministrativeAreaSimpleApi(FetchAdministrativeAreaUseCase fetchAdministrativeAreaUseCase, FilterAdministrativeAreaUseCase filterAdministrativeAreaUseCase) {
        this.fetchAdministrativeAreaUseCase = fetchAdministrativeAreaUseCase;
        this.filterAdministrativeAreaUseCase = filterAdministrativeAreaUseCase;
    }

    @Override
    public ResponseEntity<AdministrativeAreaResponseDto> loadAdministrativeAreaSimple(Integer id) {
        var simpleArea = fetchAdministrativeAreaUseCase.findSimpleArea(areaMapper.mapToAdmAreaId(id));
        AdministrativeAreaResponseDto dto = areaMapper.mapToExternal(simpleArea);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<List<AdministrativeAreaResponseDto>> getAdministrativeAreasByLevelAndCountrySimple(String levelName, String countryName) {
        var areas = filterAdministrativeAreaUseCase.findSimpleByLevelAndCountry(levelName, countryName);
        var dtos = areas.stream().map(areaMapper::mapToExternal).toList();
        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<List<AdministrativeAreaResponseDto>> getAdministrativeAreasByMetadataSimple(String metadataQuery) {
        var areas = filterAdministrativeAreaUseCase.findSimpleByMetadataContains(metadataQuery);
        var dtos = areas.stream().map(areaMapper::mapToExternal).toList();
        return ResponseEntity.ok(dtos);
    }
}
