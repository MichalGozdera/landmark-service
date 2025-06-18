package eu.cokeman.cycleareastats.in.rest;

import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.mapper.country.CountryExternalMapper;
import eu.cokeman.cycleareastats.openapi.model.CountryDto;
import eu.cokeman.cycleareastats.openapi.model.CountryRequestDto;
import eu.cokeman.cycleareastats.port.in.country.CreateCountryUseCase;
import eu.cokeman.cycleareastats.port.in.country.DeleteCountryUseCase;
import eu.cokeman.cycleareastats.port.in.country.FetchCountryUseCase;
import eu.cokeman.cycleareastats.valueObject.CountryId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CountryApi implements eu.cokeman.cycleareastats.openapi.api.CountryApi {
    private final FetchCountryUseCase fetchCountryUseCase;
    private final CreateCountryUseCase createCountryUseCase;
    private final DeleteCountryUseCase deleteCountryUseCase;
    CountryExternalMapper countryMapper = CountryExternalMapper.INSTANCE;

    public CountryApi(FetchCountryUseCase fetchCountryUseCase,
                      CreateCountryUseCase createCountryUseCase,
                      DeleteCountryUseCase deleteCountryUseCase) {
        this.fetchCountryUseCase = fetchCountryUseCase;
        this.createCountryUseCase = createCountryUseCase;
        this.deleteCountryUseCase = deleteCountryUseCase;
    }

    @Override
    public ResponseEntity<List<CountryDto>> getCountries() {
        List<CountryDto> countries = fetchCountryUseCase.findAll().stream()
                .map(countryMapper::mapToExternal)
                .collect(Collectors.toList());
        return ResponseEntity.ok(countries);
    }

    @Override
    public ResponseEntity<CountryDto> getCountryById(Integer id) {
        Country country = fetchCountryUseCase.findById(new CountryId(id));
        return ResponseEntity.ok(countryMapper.mapToExternal(country));
    }

    @Override
    public ResponseEntity<CountryDto> createCountry(CountryRequestDto countryRequest) {
        Country country = Country.builder().name(countryRequest.getName()).build();
        CountryId countryId = createCountryUseCase.create(country);
        Country created = fetchCountryUseCase.findById(countryId);
        return ResponseEntity.status(201).body(countryMapper.mapToExternal(created));
    }

    @Override
    public ResponseEntity<Void> deleteCountry(Integer id) {
        deleteCountryUseCase.delete(new CountryId(id));
        return ResponseEntity.noContent().build();
    }
}
