package eu.cokeman.cycleareastats.in.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeAreaResponseDto;
import eu.cokeman.cycleareastats.port.in.administrativearea.FetchAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.in.administrativearea.FilterAdministrativeAreaUseCase;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class AdministrativeAreaSimpleApiTest {
  @Mock private FetchAdministrativeAreaUseCase fetchAdministrativeAreaUseCase;
  @Mock private FilterAdministrativeAreaUseCase filterAdministrativeAreaUseCase;
  @InjectMocks private AdministrativeAreaSimpleApi administrativeAreaSimpleApi;

  @Test
  void shouldLoadAdministrativeAreaSimple() {
    AdministrativeArea area =
        AdministrativeArea.builder()
            .id(new eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId(1))
            .name(new eu.cokeman.cycleareastats.valueObject.AreaName("Test Area 1"))
            .build();
    when(fetchAdministrativeAreaUseCase.findSimpleArea(any())).thenReturn(area);
    ResponseEntity<AdministrativeAreaResponseDto> response =
        administrativeAreaSimpleApi.loadAdministrativeAreaSimple(1);
    assertEquals(200, response.getStatusCode().value());
    assertNotNull(response.getBody());
    assertEquals("Test Area 1", response.getBody().getName());
  }

  @Test
  void shouldGetAdministrativeAreasByLevelAndCountrySimple() {
    AdministrativeArea area =
        AdministrativeArea.builder()
            .id(new eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId(2))
            .name(new eu.cokeman.cycleareastats.valueObject.AreaName("Test Area 2"))
            .build();
    when(filterAdministrativeAreaUseCase.findSimpleByLevelAndCountry("CITY", "Poland"))
        .thenReturn(List.of(area));
    ResponseEntity<List<AdministrativeAreaResponseDto>> response =
        administrativeAreaSimpleApi.getAdministrativeAreasByLevelAndCountrySimple("CITY", "Poland");
    assertEquals(200, response.getStatusCode().value());
    assertEquals(1, response.getBody().size());
    assertEquals("Test Area 2", response.getBody().get(0).getName());
  }

  @Test
  void shouldGetAdministrativeAreasByMetadataSimple() {
    AdministrativeArea area =
        AdministrativeArea.builder()
            .id(new eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId(3))
            .name(new eu.cokeman.cycleareastats.valueObject.AreaName("Test Area 3"))
            .build();
    when(filterAdministrativeAreaUseCase.findSimpleByMetadataContains("meta"))
        .thenReturn(List.of(area));
    ResponseEntity<List<AdministrativeAreaResponseDto>> response =
        administrativeAreaSimpleApi.getAdministrativeAreasByMetadataSimple("meta");
    assertEquals(200, response.getStatusCode().value());
    assertEquals(1, response.getBody().size());
    assertEquals("Test Area 3", response.getBody().get(0).getName());
  }
}
