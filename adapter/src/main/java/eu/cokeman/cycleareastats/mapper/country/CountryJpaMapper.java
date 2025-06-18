package eu.cokeman.cycleareastats.mapper.country;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.mapper.level.AdministrativeLevelCommonMapper;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeLevelEntity;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.CountryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CountryJpaMapper extends CountryExternalMapper {

    public static CountryJpaMapper INSTANCE = Mappers.getMapper(CountryJpaMapper.class);

    CountryEntity mapToJpa (Country country);

    Country.Builder mapJpaToInternal (CountryEntity countryEntity);

}
