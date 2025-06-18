package eu.cokeman.cycleareastats.mapper.country;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.mapper.level.AdministrativeLevelCommonMapper;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeLevelDto;
import eu.cokeman.cycleareastats.openapi.model.CountryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CountryExternalMapper extends CountryCommonMapper {

    public static CountryExternalMapper INSTANCE = Mappers.getMapper(CountryExternalMapper.class);

    Country.Builder mapToInternal(CountryDto countryDto);

    CountryDto mapToExternal(Country country);

}
