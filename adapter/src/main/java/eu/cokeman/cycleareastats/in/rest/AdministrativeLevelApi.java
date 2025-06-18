package eu.cokeman.cycleareastats.in.rest;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.mapper.level.AdministrativeLevelExternalMapper;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeLevelDto;
import eu.cokeman.cycleareastats.port.in.administrativearea.DeleteAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.in.administrativearea.UpdateAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.in.administrativelevel.CreateAdministrativeLevelUseCase;
import eu.cokeman.cycleareastats.port.in.administrativelevel.DeleteAdministrativeLevelUseCase;
import eu.cokeman.cycleareastats.port.in.administrativelevel.FetchAdministrativeLevelUseCase;
import eu.cokeman.cycleareastats.port.in.administrativelevel.UpdateAdministrativeLevelUseCase;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
public class AdministrativeLevelApi implements eu.cokeman.cycleareastats.openapi.api.AdministrativeLevelApi {

    private final CreateAdministrativeLevelUseCase createAdministrativeLevelUseCase;
    private final FetchAdministrativeLevelUseCase fetchAdministrativeLevelUseCase;
    private final UpdateAdministrativeLevelUseCase updateAdministrativeLevelUseCase;
    private final DeleteAdministrativeLevelUseCase deleteAdministrativeLevelUseCase;

    AdministrativeLevelExternalMapper mapper = AdministrativeLevelExternalMapper.INSTANCE;

    public AdministrativeLevelApi(CreateAdministrativeLevelUseCase createAdministrativeLevelUseCase
            , FetchAdministrativeLevelUseCase fetchAdministrativeLevelUseCase
            , UpdateAdministrativeLevelUseCase updateAdministrativeLevelUseCase
            , DeleteAdministrativeLevelUseCase deleteAdministrativeLevelUseCase
    ) {
        this.createAdministrativeLevelUseCase = createAdministrativeLevelUseCase;
        this.fetchAdministrativeLevelUseCase = fetchAdministrativeLevelUseCase;
        this.updateAdministrativeLevelUseCase = updateAdministrativeLevelUseCase;
        this.deleteAdministrativeLevelUseCase = deleteAdministrativeLevelUseCase;

    }

    @Override
    public ResponseEntity<Void> createAdministrativeLevel(AdministrativeLevelDto levelCreateRequestDto) {
        AdministrativeLevel level = mapper.mapToInternal(levelCreateRequestDto).build();
        createAdministrativeLevelUseCase.createLevel(level);
        return ResponseEntity.ok().build();
    }


    @Override
    public ResponseEntity<AdministrativeLevelDto> loadAdministrativeLevel(Integer levelId) {
        AdministrativeLevel level = fetchAdministrativeLevelUseCase.findLevel(new AdministrativeLevelId(levelId));
        var levelResponse = mapper.mapToExternal(level);
        return ResponseEntity.ok(levelResponse);
    }

    @Override
    public ResponseEntity<AdministrativeLevelDto> deleteAdministrativeLevel(Integer administrativeLevelId) {
        AdministrativeLevelId areaInternalId = new AdministrativeLevelId(administrativeLevelId);
        deleteAdministrativeLevelUseCase.deleteAdministrativeLevel(areaInternalId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<AdministrativeLevelDto> updateAdministrativeLevel(Integer administrativeLevelId, AdministrativeLevelDto administrativeLevelDto) {
        AdministrativeLevelId areaInternalId = new AdministrativeLevelId(administrativeLevelId);
        AdministrativeLevel level = mapper.mapToInternal(administrativeLevelDto).build();
        AdministrativeLevel updatedLevel = updateAdministrativeLevelUseCase.updateAdministrativeLevel(areaInternalId, level);
        var updatedLevelDto = mapper.mapToExternal(updatedLevel);
        return ResponseEntity.ok(updatedLevelDto);
    }
}
