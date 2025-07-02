package eu.cokeman.cycleareastats.service.level;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.exception.CountryNotFoundException;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.port.out.persistence.CountryRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        // Create a real Country object
        var country = eu.cokeman.cycleareastats.entity.Country.builder()
                .id(null)
                .name("Polska")
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        AdministrativeLevel level = AdministrativeLevel.Builder.builder()
                .id(id)
                .country(country)
                .order(new eu.cokeman.cycleareastats.valueObject.LevelOrder(1))
                .name(new eu.cokeman.cycleareastats.valueObject.LevelName("CITY"))
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        AdministrativeLevel boundLevel = level;
        when(levelRepository.updateLevel(id, boundLevel)).thenReturn(boundLevel);
        // Mock CountryRepository to avoid exception in binder
        // Binder uses findByName
        when(countryRepository.findByName("Polska")).thenReturn(java.util.Optional.of(country));

        AdministrativeLevel result = service.updateAdministrativeLevel(id, level);

        assertEquals(boundLevel, result);
        verify(levelRepository, times(1)).updateLevel(id, boundLevel);
    }

    @Test
    void shouldThrowExceptionWhenCountryNotFound() {
        AdministrativeLevelId id = new AdministrativeLevelId(1);
        var country = eu.cokeman.cycleareastats.entity.Country.builder()
                .id(null)
                .name("NieistniejacyKraj")
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        AdministrativeLevel level = AdministrativeLevel.Builder.builder()
                .id(id)
                .country(country)
                .order(new eu.cokeman.cycleareastats.valueObject.LevelOrder(1))
                .name(new eu.cokeman.cycleareastats.valueObject.LevelName("CITY"))
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        // Binder uses findByName, return empty
        when(countryRepository.findByName("NieistniejacyKraj")).thenReturn(java.util.Optional.empty());

        assertThrows(CountryNotFoundException.class, () -> service.updateAdministrativeLevel(id, level));
    }
}
