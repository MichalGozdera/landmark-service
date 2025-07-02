package eu.cokeman.cycleareastats.service.country;

import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.port.out.persistence.CountryRepository;
import eu.cokeman.cycleareastats.valueObject.CountryId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCountryServiceTest {
    private CountryRepository countryRepository;
    private CreateCountryService service;

    @BeforeEach
    void setUp() {
        countryRepository = mock(CountryRepository.class);
        service = new CreateCountryService(countryRepository);
    }

    @Test
    void shouldDelegateCreateToRepository() {
        Country country = Country.builder()
                .id(null)
                .name("Polska")
                .createTime(Instant.now())
                .updateTime(Instant.now())
                .build();
        CountryId expectedId = new CountryId(1);
        when(countryRepository.create(country)).thenReturn(expectedId);

        CountryId result = service.create(country);

        assertEquals(expectedId, result);
        verify(countryRepository, times(1)).create(country);
    }
}

