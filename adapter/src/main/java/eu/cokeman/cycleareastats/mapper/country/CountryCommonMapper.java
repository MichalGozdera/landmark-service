package eu.cokeman.cycleareastats.mapper.country;

import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.CountryEntity;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;
import eu.cokeman.cycleareastats.valueObject.CountryId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CountryCommonMapper {

    CountryCommonMapper INSTANCE = Mappers.getMapper(CountryCommonMapper.class);
    default CountryId mapToCountryId(Integer source) {
        return source == null ? null : new CountryId(source);
    }

    default Integer mapCountryIDToInteger(CountryId source) {
        return source != null ? source.value() : null;
    }
}

