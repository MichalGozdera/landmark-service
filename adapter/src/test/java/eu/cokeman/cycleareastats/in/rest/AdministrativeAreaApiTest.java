package eu.cokeman.cycleareastats.in.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import eu.cokeman.cycleareastats.openapi.model.AdministrativeAreaRequestDto;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeAreaResponseDto;
import eu.cokeman.cycleareastats.openapi.model.CreateAdministrativeAreaRequestDto;
import eu.cokeman.cycleareastats.service.AreaApplicationService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class AdministrativeAreaApiTest {
  @Mock private AreaApplicationService areaApplicationService;
  @InjectMocks private AdministrativeAreaApi administrativeAreaApi;

  @Test
  void shouldLoadAdministrativeArea() {
    AdministrativeAreaResponseDto dto = new AdministrativeAreaResponseDto();
    dto.setId(1);
    when(areaApplicationService.loadAdministrativeArea(1)).thenReturn(dto);

    ResponseEntity<AdministrativeAreaResponseDto> response =
        administrativeAreaApi.loadAdministrativeArea(1);
    assertEquals(200, response.getStatusCode().value());
    assertEquals(1, response.getBody().getId());
  }

  @Test
  void shouldUpdateAdministrativeArea() {
    AdministrativeAreaRequestDto requestDto = new AdministrativeAreaRequestDto();
    AdministrativeAreaResponseDto responseDto = new AdministrativeAreaResponseDto();
    responseDto.setId(2);
    when(areaApplicationService.updateAdministrativeArea(2, requestDto)).thenReturn(responseDto);

    ResponseEntity<AdministrativeAreaResponseDto> response =
        administrativeAreaApi.updateAdministrativeArea(2, requestDto);
    assertEquals(200, response.getStatusCode().value());
    assertEquals(2, response.getBody().getId());
  }

  @Test
  void shouldDeleteAdministrativeArea() {
    doNothing().when(areaApplicationService).deleteAdministrativeArea(3);
    ResponseEntity<Void> response = administrativeAreaApi.deleteAdministrativeArea(3);
    assertEquals(200, response.getStatusCode().value());
    verify(areaApplicationService, times(1)).deleteAdministrativeArea(3);
  }

  @Test
  void shouldCreateAdministrativeArea() {
    CreateAdministrativeAreaRequestDto createDto = new CreateAdministrativeAreaRequestDto();
    var id = new eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId(4);
    when(areaApplicationService.createAdministrativeArea(createDto)).thenReturn(id);

    ResponseEntity<Void> response = administrativeAreaApi.createAdministrativeArea(createDto);
    assertEquals(201, response.getStatusCode().value());
    assertTrue(response.getHeaders().getLocation().toString().endsWith("/administrative-areas/4"));
  }

  @Test
  void shouldGetAdministrativeAreasByLevelAndCountry() {
    AdministrativeAreaResponseDto dto = new AdministrativeAreaResponseDto();
    dto.setId(5);
    when(areaApplicationService.getAdministrativeAreasByLevelAndCountry("CITY", "Poland"))
        .thenReturn(List.of(dto));

    ResponseEntity<List<AdministrativeAreaResponseDto>> response =
        administrativeAreaApi.getAdministrativeAreasByLevelAndCountry("CITY", "Poland");
    assertEquals(200, response.getStatusCode().value());
    assertEquals(1, response.getBody().size());
    assertEquals(5, response.getBody().get(0).getId());
  }

  @Test
  void shouldGetAdministrativeAreasByMetadata() {
    AdministrativeAreaResponseDto dto = new AdministrativeAreaResponseDto();
    dto.setId(6);
    when(areaApplicationService.getAdministrativeAreasByMetadata("test-metadata"))
        .thenReturn(List.of(dto));

    ResponseEntity<List<AdministrativeAreaResponseDto>> response =
        administrativeAreaApi.getAdministrativeAreasByMetadata("test-metadata");
    assertEquals(200, response.getStatusCode().value());
    assertEquals(1, response.getBody().size());
    assertEquals(6, response.getBody().get(0).getId());
  }
}
