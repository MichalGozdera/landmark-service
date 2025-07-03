package eu.cokeman.cycleareastats.in.rest;

import eu.cokeman.cycleareastats.openapi.model.AdministrativeAreaRequestDto;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeAreaResponseDto;
import eu.cokeman.cycleareastats.openapi.model.CreateAdministrativeAreaRequestDto;
import eu.cokeman.cycleareastats.service.AreaApplicationService;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministrativeAreaApi
    implements eu.cokeman.cycleareastats.openapi.api.AdministrativeAreaApi {

  private final AreaApplicationService areaApplicationService;

  public AdministrativeAreaApi(AreaApplicationService areaApplicationService) {
    this.areaApplicationService = areaApplicationService;
  }

  @Override
  public ResponseEntity<AdministrativeAreaResponseDto> loadAdministrativeArea(
      Integer areaIdExternal) {
    AdministrativeAreaResponseDto areaResponse =
        areaApplicationService.loadAdministrativeArea(areaIdExternal);
    return ResponseEntity.ok(areaResponse);
  }

  @Override
  public ResponseEntity<AdministrativeAreaResponseDto> updateAdministrativeArea(
      Integer administrativeAreaId, AdministrativeAreaRequestDto administrativeAreaDto) {
    AdministrativeAreaResponseDto updatedAreaDto =
        areaApplicationService.updateAdministrativeArea(
            administrativeAreaId, administrativeAreaDto);
    return ResponseEntity.ok(updatedAreaDto);
  }

  @Override
  public ResponseEntity<Void> deleteAdministrativeArea(Integer administrativeAreaId) {
    areaApplicationService.deleteAdministrativeArea(administrativeAreaId);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Void> createAdministrativeArea(
      CreateAdministrativeAreaRequestDto createAdministrativeAreaRequestDto) {
    var id = areaApplicationService.createAdministrativeArea(createAdministrativeAreaRequestDto);
    return ResponseEntity.created(URI.create("/administrative-areas/" + id.value())).build();
  }

  @Override
  public ResponseEntity<List<AdministrativeAreaResponseDto>>
      getAdministrativeAreasByLevelAndCountry(String levelName, String countryName) {
    List<AdministrativeAreaResponseDto> dtos =
        areaApplicationService.getAdministrativeAreasByLevelAndCountry(levelName, countryName);
    return ResponseEntity.ok(dtos);
  }

  @Override
  public ResponseEntity<List<AdministrativeAreaResponseDto>> getAdministrativeAreasByMetadata(
      String metadataQuery) {
    List<AdministrativeAreaResponseDto> dtos =
        areaApplicationService.getAdministrativeAreasByMetadata(metadataQuery);
    return ResponseEntity.ok(dtos);
  }
}
