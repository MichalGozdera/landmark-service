package eu.cokeman.cycleareastats.service.country;

import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.port.in.country.FetchCountryUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.CountryRepository;
import eu.cokeman.cycleareastats.valueObject.CountryId;
import java.util.List;

public class FetchCountryService implements FetchCountryUseCase {
  private final CountryRepository countryRepository;

  public FetchCountryService(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  @Override
  public Country findById(CountryId id) {
    return countryRepository.findById(id);
  }

  @Override
  public List<Country> findAll() {
    return countryRepository.findAll();
  }
}
