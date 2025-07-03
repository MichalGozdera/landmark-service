package eu.cokeman.cycleareastats.service.country;

import static org.mockito.Mockito.*;

import eu.cokeman.cycleareastats.port.out.persistence.CountryRepository;
import eu.cokeman.cycleareastats.valueObject.CountryId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeleteCountryServiceTest {
  private CountryRepository countryRepository;
  private DeleteCountryService service;

  @BeforeEach
  void setUp() {
    countryRepository = mock(CountryRepository.class);
    service = new DeleteCountryService(countryRepository);
  }

  @Test
  void shouldDelegateDeleteToRepository() {
    CountryId id = new CountryId(1);
    service.delete(id);
    verify(countryRepository, times(1)).delete(id);
  }
}
