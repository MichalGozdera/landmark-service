package eu.cokeman.cycleareastats.mapper.area;

import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.AreaName;
import eu.cokeman.cycleareastats.valueObject.LevelName;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper
public interface AdministrativeAreaCommonMapper {

    default AreaName mapToAreaName(String source) {
        return new AreaName(source);
    }

    default String mapAdministrativeNameToString(AreaName source) {
        return source.name();
    }


    default AdministrativeAreaId mapToAdmAreaId(Integer source) {
        return source == null ? null : new AdministrativeAreaId(source);
    }

    default Integer mapAdministrativeAreaIDToInteger(AdministrativeAreaId source) {
        return source != null ? source.value() : null;
    }
}
