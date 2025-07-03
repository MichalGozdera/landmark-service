package eu.cokeman.cycleareastats.in.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeAreasImportRequestDto;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeLevelBasicDto;
import eu.cokeman.cycleareastats.port.in.administrativearea.ExportAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.in.administrativearea.ImportAdministrativeAreaUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
class AdministrativeAreaImportExportApiTest {
  @Mock private ImportAdministrativeAreaUseCase importAdministrativeAreaUseCase;
  @Mock private ExportAdministrativeAreaUseCase exportAdministrativeAreaUseCase;
  @InjectMocks private AdministrativeAreaImportExportApi administrativeAreaImportExportApi;

  @Test
  void shouldExportAdministrativeAreasKml() {
    when(exportAdministrativeAreaUseCase.exportKmlByCountryAndLevel("Poland", "CITY"))
        .thenReturn("<kml>data</kml>");
    ResponseEntity<Resource> response =
        administrativeAreaImportExportApi.exportAdministrativeAreasKml("Poland", "CITY", "test");
    assertEquals(200, response.getStatusCode().value());
    assertTrue(response.getHeaders().get("Content-Disposition").get(0).contains("test.kml"));
    assertNotNull(response.getBody());
  }

  @Test
  void shouldImportAdministrativeAreasWithValidKml() {
    AdministrativeAreasImportRequestDto requestDto = new AdministrativeAreasImportRequestDto();
    AdministrativeLevelBasicDto levelDto = new AdministrativeLevelBasicDto();
    levelDto.setName("CITY");
    levelDto.setCountry("Poland");
    requestDto.setLevel(levelDto);
    MultipartFile file = mock(MultipartFile.class);
    when(file.getOriginalFilename()).thenReturn("test.kml");
    doNothing()
        .when(importAdministrativeAreaUseCase)
        .importAdministrativeAreas(any(AdministrativeLevel.class), any(), eq(file));
    ResponseEntity<Void> response =
        administrativeAreaImportExportApi.importAdministrativeAreas(requestDto, file);
    assertEquals(200, response.getStatusCode().value());
  }

  @Test
  void shouldRejectImportIfFileIsNotKml() {
    AdministrativeAreasImportRequestDto requestDto = new AdministrativeAreasImportRequestDto();
    MultipartFile file = mock(MultipartFile.class);
    when(file.getOriginalFilename()).thenReturn("test.txt");
    ResponseEntity<Void> response =
        administrativeAreaImportExportApi.importAdministrativeAreas(requestDto, file);
    assertEquals(400, response.getStatusCode().value());
    assertTrue(response.getHeaders().containsKey("X-Error-Message"));
  }
}
