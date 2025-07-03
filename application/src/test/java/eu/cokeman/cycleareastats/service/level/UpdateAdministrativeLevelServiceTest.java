package eu.cokeman.cycleareastats.service.level;

import static eu.cokeman.cycleareastats.service.TestEntityFactory.country;
import static eu.cokeman.cycleareastats.service.TestEntityFactory.level;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.exception.CountryNotFoundException;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.port.out.persistence.CountryRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateAdministrativeLevelServiceTest {
  private AdministrativeLevelRepository levelRepository;
  private CountryRepository countryRepository;
  private UpdateAdministrativeLevelService service;

  @BeforeEach
  void setUp() {
    levelRepository = mock(AdministrativeLevelRepository.class);
    countryRepository = mock(CountryRepository.class);
    service = new UpdateAdministrativeLevelService(levelRepository, countryRepository);
  }

  @Test
  void shouldDelegateUpdateToRepositoryAndBindCountry() {
    AdministrativeLevelId id = new AdministrativeLevelId(1);
    var country = country(null, "Poland");
    AdministrativeLevel level = level(1, country, 1, "CITY");
    AdministrativeLevel boundLevel = level;
    when(levelRepository.updateLevel(id, boundLevel)).thenReturn(boundLevel);
    // Mock CountryRepository to avoid exception in binder
    // Binder uses findByName
    when(countryRepository.findByName("Poland")).thenReturn(java.util.Optional.of(country));

    AdministrativeLevel result = service.updateAdministrativeLevel(id, level);

    assertEquals(boundLevel, result);
    verify(levelRepository, times(1)).updateLevel(id, boundLevel);
  }

  @Test
  void shouldThrowExceptionWhenCountryNotFound() {
    AdministrativeLevelId id = new AdministrativeLevelId(1);
    var country = country(null, "NonExistingCountry");
    AdministrativeLevel level = level(1, country, 1, "CITY");
    // Binder uses findByName, return empty
    when(countryRepository.findByName("NonExistingCountry")).thenReturn(java.util.Optional.empty());

    assertThrows(
        CountryNotFoundException.class, () -> service.updateAdministrativeLevel(id, level));
  }
}
