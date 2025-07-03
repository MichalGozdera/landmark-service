package eu.cokeman.cycleareastats.mapper.area;

import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.AreaName;
import org.mapstruct.Mapper;

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
