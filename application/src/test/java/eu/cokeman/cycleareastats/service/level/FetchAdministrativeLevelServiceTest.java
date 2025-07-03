package eu.cokeman.cycleareastats.service.level;

import static eu.cokeman.cycleareastats.service.TestEntityFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FetchAdministrativeLevelServiceTest {
  private AdministrativeLevelRepository levelRepository;
  private FetchAdministrativeLevelService service;

  @BeforeEach
  void setUp() {
    levelRepository = mock(AdministrativeLevelRepository.class);
    service = new FetchAdministrativeLevelService(levelRepository);
  }

  @Test
  @DisplayName("Returns administrative level by ID")
  void shouldFetchLevelById() {
    AdministrativeLevelId id = new AdministrativeLevelId(1);
    var country = country(1, "Poland");
    AdministrativeLevel level = level(1, country, 1, "LEVEL");
    when(levelRepository.findByAdministrativeLevelId(id)).thenReturn(level);

    AdministrativeLevel result = service.findLevel(id);

    Assertions.assertThat(result).isNotNull();
    assertEquals(level, result);
    verify(levelRepository, times(1)).findByAdministrativeLevelId(id);
  }

  @Test
  @DisplayName("Returns null if administrative level not found")
  void shouldReturnNullIfLevelNotFound() {
    AdministrativeLevelId id = new AdministrativeLevelId(2);
    when(levelRepository.findByAdministrativeLevelId(id)).thenReturn(null);

    AdministrativeLevel result = service.findLevel(id);

    assertNull(result);
    verify(levelRepository, times(1)).findByAdministrativeLevelId(id);
  }
}
