package eu.cokeman.cycleareastats.in.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeLevelDto;
import eu.cokeman.cycleareastats.port.in.administrativelevel.CreateAdministrativeLevelUseCase;
import eu.cokeman.cycleareastats.port.in.administrativelevel.DeleteAdministrativeLevelUseCase;
import eu.cokeman.cycleareastats.port.in.administrativelevel.FetchAdministrativeLevelUseCase;
import eu.cokeman.cycleareastats.port.in.administrativelevel.UpdateAdministrativeLevelUseCase;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;
import eu.cokeman.cycleareastats.valueObject.LevelName;
import eu.cokeman.cycleareastats.valueObject.LevelOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class AdministrativeLevelApiTest {
  @Mock private CreateAdministrativeLevelUseCase createAdministrativeLevelUseCase;
  @Mock private FetchAdministrativeLevelUseCase fetchAdministrativeLevelUseCase;
  @Mock private UpdateAdministrativeLevelUseCase updateAdministrativeLevelUseCase;
  @Mock private DeleteAdministrativeLevelUseCase deleteAdministrativeLevelUseCase;
  @InjectMocks private AdministrativeLevelApi administrativeLevelApi;

  @Test
  void shouldCreateAdministrativeLevel() {
    AdministrativeLevelDto dto = new AdministrativeLevelDto();
    dto.setName("CITY");
    dto.setCountry("Poland");
    dto.setOrder(1);
    AdministrativeLevelId id = new AdministrativeLevelId(1);
    when(createAdministrativeLevelUseCase.createLevel(any(AdministrativeLevel.class)))
        .thenReturn(id);
    ResponseEntity<Void> response = administrativeLevelApi.createAdministrativeLevel(dto);
    assertEquals(201, response.getStatusCode().value());
    assertTrue(response.getHeaders().getLocation().toString().endsWith("/administrative-levels/1"));
  }

  @Test
  void shouldLoadAdministrativeLevel() {
    var country =
        eu.cokeman.cycleareastats.entity.Country.builder()
            .id(new eu.cokeman.cycleareastats.valueObject.CountryId(10))
            .name("Poland")
            .build();
    AdministrativeLevel level =
        AdministrativeLevel.builder()
            .id(new AdministrativeLevelId(2))
            .name(new LevelName("CITY"))
            .country(country)
            .build();
    when(fetchAdministrativeLevelUseCase.findLevel(new AdministrativeLevelId(2))).thenReturn(level);
    ResponseEntity<AdministrativeLevelDto> response =
        administrativeLevelApi.loadAdministrativeLevel(2);
    assertEquals(200, response.getStatusCode().value());
    assertEquals("CITY", response.getBody().getName());
    assertEquals("Poland", response.getBody().getCountry());
  }

  @Test
  void shouldUpdateAdministrativeLevel() {
    AdministrativeLevelDto dto = new AdministrativeLevelDto();
    dto.setName("CITY");
    dto.setCountry("Poland");
    dto.setOrder(2);
    var country =
        eu.cokeman.cycleareastats.entity.Country.builder()
            .id(new eu.cokeman.cycleareastats.valueObject.CountryId(11))
            .name("Poland")
            .build();
    AdministrativeLevel level =
        AdministrativeLevel.builder()
            .id(new AdministrativeLevelId(3))
            .order(new LevelOrder(2))
            .country(country)
            .name(new LevelName("CITY"))
            .build();
    when(updateAdministrativeLevelUseCase.updateAdministrativeLevel(
            eq(new AdministrativeLevelId(3)), any(AdministrativeLevel.class)))
        .thenReturn(level);
    ResponseEntity<AdministrativeLevelDto> response =
        administrativeLevelApi.updateAdministrativeLevel(3, dto);
    assertEquals(200, response.getStatusCode().value());
    assertEquals("CITY", response.getBody().getName());
    assertEquals("Poland", response.getBody().getCountry());
    assertEquals(2, response.getBody().getOrder());
  }

  @Test
  void shouldDeleteAdministrativeLevel() {
    doNothing()
        .when(deleteAdministrativeLevelUseCase)
        .deleteAdministrativeLevel(new AdministrativeLevelId(4));
    ResponseEntity<Void> response = administrativeLevelApi.deleteAdministrativeLevel(4);
    assertEquals(200, response.getStatusCode().value());
    verify(deleteAdministrativeLevelUseCase, times(1))
        .deleteAdministrativeLevel(new AdministrativeLevelId(4));
  }
}
