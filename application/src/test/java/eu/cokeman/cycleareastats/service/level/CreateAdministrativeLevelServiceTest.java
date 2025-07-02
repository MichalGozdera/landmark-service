package eu.cokeman.cycleareastats.service.level;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.port.out.persistence.CountryRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;
import eu.cokeman.cycleareastats.valueObject.CountryId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateAdministrativeLevelServiceTest {
    private AdministrativeLevelRepository levelRepository;
    private CountryRepository countryRepository;
    private CreateAdministrativeLevelService service;

    @BeforeEach
    void setUp() {
        levelRepository = mock(eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository.class);
        countryRepository = mock(CountryRepository.class);
        service = new CreateAdministrativeLevelService(levelRepository, countryRepository);
    }

    @Test
    void shouldDelegateCreateToRepository() {
        // Arrange: create real Country object without id (simulate new country)
        var countryWithId = eu.cokeman.cycleareastats.entity.Country.builder()
                .id(new eu.cokeman.cycleareastats.valueObject.CountryId(123))
                .name("Polska")
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        // Binder expects to find country with id in repo
        var country = countryWithId.toBuilder().id(null).build();
        AdministrativeLevel level = AdministrativeLevel.Builder.builder()
                .id(null)
                .country(country)
                .order(new eu.cokeman.cycleareastats.valueObject.LevelOrder(1))
                .name(new eu.cokeman.cycleareastats.valueObject.LevelName("CITY"))
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        AdministrativeLevelId expectedId = new AdministrativeLevelId(1);
        // Binder uses findByName
        when(countryRepository.findByName("Polska")).thenReturn(java.util.Optional.of(countryWithId));
        // Binder will replace country in level with countryWithId
        AdministrativeLevel boundLevel = level.toBuilder().country(countryWithId).build();
        when(levelRepository.createLevel(boundLevel)).thenReturn(expectedId);

        // Act
        AdministrativeLevelId result = service.createLevel(level);

        // Assert
        assertEquals(expectedId, result);
        verify(countryRepository, times(1)).findByName("Polska");
        verify(levelRepository, times(1)).createLevel(boundLevel);
    }

    @Test
    void shouldDelegateCreateToRepositoryAndBindCountry() {
        // Arrange: create real AdministrativeLevel and mock only what binder uses
        var country = eu.cokeman.cycleareastats.entity.Country.builder()
                .id(null)
                .name("Polska")
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        var countryWithId = country.toBuilder().id(new eu.cokeman.cycleareastats.valueObject.CountryId(123)).build();
        AdministrativeLevel level = AdministrativeLevel.Builder.builder()
                .id(null)
                .country(country)
                .order(new eu.cokeman.cycleareastats.valueObject.LevelOrder(1))
                .name(new eu.cokeman.cycleareastats.valueObject.LevelName("CITY"))
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        // Binder uses findByName
        when(countryRepository.findByName("Polska")).thenReturn(java.util.Optional.of(countryWithId));
        AdministrativeLevelId expectedId = new AdministrativeLevelId(1);
        // After binding, level should have countryWithId
        when(levelRepository.createLevel(level)).thenReturn(expectedId);

        // Act
        AdministrativeLevelId result = service.createLevel(level);

        // Assert
        assertEquals(expectedId, result);
        verify(countryRepository, times(1)).findByName("Polska");
        verify(levelRepository, times(1)).createLevel(level);
    }
}
