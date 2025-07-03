package eu.cokeman.cycleareastats.service.area;

import static eu.cokeman.cycleareastats.service.TestEntityFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FetchAdministrativeAreaServiceTest {
  private AdministrativeAreaRepository repository;
  private FetchAdministrativeAreaService service;

  @BeforeEach
  void setUp() {
    repository = mock(AdministrativeAreaRepository.class);
    service = new FetchAdministrativeAreaService(repository);
  }

  @Test
  void shouldDelegateFindAreaToRepository() {
    AdministrativeAreaId id = new AdministrativeAreaId(1);
    var country = country(1, "Poland");
    var level = level(1, country, 1, "CITY");
    AdministrativeArea area = area(1, "Area1", country, level);
    when(repository.findByAdministrativeAreaId(id)).thenReturn(area);
    assertEquals(area, service.findArea(id));
    verify(repository, times(1)).findByAdministrativeAreaId(id);
  }

  @Test
  void shouldDelegateFindSimpleAreaToRepository() {
    AdministrativeAreaId id = new AdministrativeAreaId(2);
    var country = country(2, "Poland");
    var level = level(2, country, 1, "CITY");
    AdministrativeArea area = area(2, "Area2", country, level);
    when(repository.findSimpleByAdministrativeAreaId(id)).thenReturn(area);
    assertEquals(area, service.findSimpleArea(id));
    verify(repository, times(1)).findSimpleByAdministrativeAreaId(id);
  }
}
