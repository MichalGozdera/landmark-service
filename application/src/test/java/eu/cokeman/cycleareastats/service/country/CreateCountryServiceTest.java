package eu.cokeman.cycleareastats.service.country;

import static eu.cokeman.cycleareastats.service.TestEntityFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.port.out.persistence.CountryRepository;
import eu.cokeman.cycleareastats.valueObject.CountryId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    Country country = country(null, "Poland");
    CountryId expectedId = new CountryId(1);
    when(countryRepository.create(country)).thenReturn(expectedId);

    CountryId result = service.create(country);

    assertEquals(expectedId, result);
    verify(countryRepository, times(1)).create(country);
  }
}
