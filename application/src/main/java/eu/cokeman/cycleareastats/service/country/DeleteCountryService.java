package eu.cokeman.cycleareastats.service.country;

import eu.cokeman.cycleareastats.port.in.country.DeleteCountryUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.CountryRepository;
import eu.cokeman.cycleareastats.valueObject.CountryId;

public class DeleteCountryService implements DeleteCountryUseCase {
  private final CountryRepository countryRepository;

  public DeleteCountryService(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  @Override
  public void delete(CountryId id) {
    countryRepository.delete(id);
  }
}
