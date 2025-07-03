package eu.cokeman.cycleareastats.port.out.persistence;

import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.valueObject.CountryId;
import java.util.List;
import java.util.Optional;

public interface CountryRepository {
  Country findById(CountryId id);

  Optional<Country> findByName(String name);

  List<Country> findAll();

  CountryId create(Country country);

  void delete(CountryId id);

  Country update(CountryId id, Country country);
}
