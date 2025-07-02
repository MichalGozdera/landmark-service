package eu.cokeman.cycleareastats.service.country;

import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.port.out.persistence.CountryRepository;
import eu.cokeman.cycleareastats.valueObject.CountryId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FetchCountryServiceTest {
    private CountryRepository countryRepository;
    private FetchCountryService service;

    @BeforeEach
    void setUp() {
        countryRepository = mock(CountryRepository.class);
        service = new FetchCountryService(countryRepository);
    }

    @Test
    void shouldFindById() {
        CountryId id = new CountryId(1);
        Country country = Country.builder().id(id).name("Polska").createTime(Instant.now()).updateTime(Instant.now()).build();
        when(countryRepository.findById(id)).thenReturn(country);
        Country result = service.findById(id);
        assertEquals(country, result);
        verify(countryRepository, times(1)).findById(id);
    }

    @Test
    void shouldFindAll() {
        Country country = Country.builder().id(new CountryId(1)).name("Polska").createTime(Instant.now()).updateTime(Instant.now()).build();
        when(countryRepository.findAll()).thenReturn(List.of(country));
        List<Country> result = service.findAll();
        assertEquals(1, result.size());
        verify(countryRepository, times(1)).findAll();
    }
}

