package eu.cokeman.cycleareastats.in.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.openapi.model.CountryDto;
import eu.cokeman.cycleareastats.openapi.model.CountryRequestDto;
import eu.cokeman.cycleareastats.port.in.country.CreateCountryUseCase;
import eu.cokeman.cycleareastats.port.in.country.DeleteCountryUseCase;
import eu.cokeman.cycleareastats.port.in.country.FetchCountryUseCase;
import eu.cokeman.cycleareastats.valueObject.CountryId;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class CountryApiTest {
  @Mock private FetchCountryUseCase fetchCountryUseCase;
  @Mock private CreateCountryUseCase createCountryUseCase;
  @Mock private DeleteCountryUseCase deleteCountryUseCase;
  @InjectMocks private CountryApi countryApi;

  @Test
  void shouldGetCountries() {
    Country country = Country.builder().id(new CountryId(1)).name("Poland").build();
    when(fetchCountryUseCase.findAll()).thenReturn(List.of(country));
    ResponseEntity<List<CountryDto>> response = countryApi.getCountries();
    assertEquals(200, response.getStatusCode().value());
    assertEquals("Poland", response.getBody().get(0).getName());
  }

  @Test
  void shouldGetCountryById() {
    Country country = Country.builder().id(new CountryId(2)).name("Czechia").build();
    when(fetchCountryUseCase.findById(new CountryId(2))).thenReturn(country);
    ResponseEntity<CountryDto> response = countryApi.getCountryById(2);
    assertEquals(200, response.getStatusCode().value());
    assertEquals("Czechia", response.getBody().getName());
  }

  @Test
  void shouldCreateCountry() {
    CountryRequestDto request = new CountryRequestDto();
    request.setName("Slovakia");
    Country country = Country.builder().name("Slovakia").build();
    CountryId countryId = new CountryId(3);
    when(createCountryUseCase.create(any(Country.class))).thenReturn(countryId);
    when(fetchCountryUseCase.findById(countryId))
        .thenReturn(Country.builder().id(countryId).name("Slovakia").build());
    ResponseEntity<CountryDto> response = countryApi.createCountry(request);
    assertEquals(201, response.getStatusCode().value());
    assertEquals("Slovakia", response.getBody().getName());
  }

  @Test
  void shouldDeleteCountry() {
    doNothing().when(deleteCountryUseCase).delete(new CountryId(4));
    ResponseEntity<Void> response = countryApi.deleteCountry(4);
    assertEquals(204, response.getStatusCode().value());
    verify(deleteCountryUseCase, times(1)).delete(new CountryId(4));
  }
}
