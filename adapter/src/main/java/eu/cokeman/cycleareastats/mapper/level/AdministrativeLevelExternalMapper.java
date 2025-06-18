package eu.cokeman.cycleareastats.mapper.level;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.mapper.country.CountryExternalMapper;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeLevelDto;
import eu.cokeman.cycleareastats.openapi.model.CountryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdministrativeLevelExternalMapper extends AdministrativeLevelCommonMapper {

    public static AdministrativeLevelExternalMapper INSTANCE = Mappers.getMapper(AdministrativeLevelExternalMapper.class);

    AdministrativeLevel.Builder mapToInternal(AdministrativeLevelDto levelDto);

    @Mapping(target = "country", source = "country.name")
    AdministrativeLevelDto mapToExternal(AdministrativeLevel level);

    default Country mapCountryToInternal(String country) {
        if (country == null) {
            return null;
        }
        return Country.builder().name(country).build();
    }


}
