package eu.cokeman.cycleareastats.service.level;

import static eu.cokeman.cycleareastats.service.TestEntityFactory.country;
import static eu.cokeman.cycleareastats.service.TestEntityFactory.level;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.port.out.persistence.CountryRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateAdministrativeLevelServiceTest {
  private AdministrativeLevelRepository levelRepository;
  private CountryRepository countryRepository;
  private CreateAdministrativeLevelService service;

  @BeforeEach
  void setUp() {
    levelRepository = mock(AdministrativeLevelRepository.class);
    countryRepository = mock(CountryRepository.class);
    service = new CreateAdministrativeLevelService(levelRepository, countryRepository);
  }

  @Test
  void shouldDelegateCreateToRepository() {
    // Arrange: create real Country object without id (simulate new country)
    var countryWithId = country(123, "Poland");
    // Binder expects to find country with id in repo
    var country = country(null, "Poland");
    AdministrativeLevel level = level(null, country, 1, "CITY");
    AdministrativeLevelId expectedId = new AdministrativeLevelId(1);
    // Binder uses findByName
    when(countryRepository.findByName("Poland")).thenReturn(java.util.Optional.of(countryWithId));
    // Binder will replace country in level with countryWithId
    AdministrativeLevel boundLevel = level.toBuilder().country(countryWithId).build();
    when(levelRepository.createLevel(boundLevel)).thenReturn(expectedId);

    // Act
    AdministrativeLevelId result = service.createLevel(level);

    // Assert
    assertEquals(expectedId, result);
    verify(countryRepository, times(1)).findByName("Poland");
    verify(levelRepository, times(1)).createLevel(boundLevel);
  }

  @Test
  void shouldDelegateCreateToRepositoryAndBindCountry() {
    // Arrange: create real AdministrativeLevel and mock only what binder uses
    var country = country(null, "Poland");
    var countryWithId = country(123, "Poland");
    AdministrativeLevel level = level(null, country, 1, "CITY");
    // Binder uses findByName
    when(countryRepository.findByName("Poland")).thenReturn(java.util.Optional.of(countryWithId));
    AdministrativeLevelId expectedId = new AdministrativeLevelId(1);
    // After binding, level should have countryWithId
    when(levelRepository.createLevel(level)).thenReturn(expectedId);

    // Act
    AdministrativeLevelId result = service.createLevel(level);

    // Assert
    assertEquals(expectedId, result);
    verify(countryRepository, times(1)).findByName("Poland");
    verify(levelRepository, times(1)).createLevel(level);
  }
}
