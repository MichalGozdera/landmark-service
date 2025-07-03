package eu.cokeman.cycleareastats.mapper.level;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.mapper.country.CountryJpaMapper;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeLevelEntity;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.CountryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdministrativeLevelJpaMapper extends AdministrativeLevelCommonMapper {

  public static AdministrativeLevelJpaMapper INSTANCE =
      Mappers.getMapper(AdministrativeLevelJpaMapper.class);

  AdministrativeLevelEntity mapToJpa(AdministrativeLevel level);

  AdministrativeLevel.Builder mapJpaToInternal(AdministrativeLevelEntity levelEntity);

  default CountryEntity mapToJpa(Country country) {
    return CountryJpaMapper.INSTANCE.mapToJpa(country);
  }

  default Country mapCountryJpaToInternal(CountryEntity country) {
    return CountryJpaMapper.INSTANCE.mapJpaToInternal(country).build();
  }
}
