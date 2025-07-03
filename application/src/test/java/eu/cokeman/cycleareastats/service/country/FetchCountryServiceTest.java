package eu.cokeman.cycleareastats.service.country;

import static eu.cokeman.cycleareastats.service.TestEntityFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.port.out.persistence.CountryRepository;
import eu.cokeman.cycleareastats.valueObject.CountryId;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FetchCountryServiceTest {
  private CountryRepository countryRepository;
  private FetchCountryService service;

  @BeforeEach
  void setUp() {
    countryRepository = mock(CountryRepository.class);
    service = new FetchCountryService(countryRepository);
  }

  @Test
  @DisplayName("Finds country by ID")
  void shouldFindById() {
    CountryId id = new CountryId(1);
    Country country = country(1, "Poland");
    when(countryRepository.findById(id)).thenReturn(country);
    Country result = service.findById(id);
    assertEquals(country, result);
    verify(countryRepository, times(1)).findById(id);
  }

  @Test
  @DisplayName("Finds all countries")
  void shouldFindAll() {
    Country country = country(1, "Poland");
    when(countryRepository.findAll()).thenReturn(List.of(country));
    List<Country> result = service.findAll();
    assertEquals(1, result.size());
    verify(countryRepository, times(1)).findAll();
  }
}
