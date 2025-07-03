package eu.cokeman.cycleareastats.service.area;

import static eu.cokeman.cycleareastats.service.TestEntityFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.port.in.administrativearea.AdministrativeAreaConverter;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExportAdministrativeAreaServiceTest {
  private AdministrativeAreaRepository repository;
  private AdministrativeAreaConverter converter;
  private ExportAdministrativeAreaService service;

  @BeforeEach
  void setUp() {
    repository = mock(AdministrativeAreaRepository.class);
    converter = mock(AdministrativeAreaConverter.class);
    service = new ExportAdministrativeAreaService(repository, converter);
  }

  @Test
  void shouldExportKmlByCountryAndLevel() {
    // Arrange: create AdministrativeArea list using TestEntityFactory
    var country = country(null, "Poland");
    var level = level(null, country, 1, "city");
    AdministrativeArea area1 = area(null, "Area1", country, level);
    AdministrativeArea area2 = area(null, "Area2", country, level);
    List<AdministrativeArea> areas = List.of(area1, area2);
    when(repository.findByLevelAndCountry("city", "Poland")).thenReturn(areas);
    when(converter.convertToKml(anyList())).thenReturn("<kml>...</kml>");

    // Act
    String result = service.exportKmlByCountryAndLevel("Poland", "city");

    // Assert
    assertEquals("<kml>...</kml>", result);
    verify(repository, times(1)).findByLevelAndCountry("city", "Poland");
    verify(converter, times(1)).convertToKml(anyList());
  }
}
