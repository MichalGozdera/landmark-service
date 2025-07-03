package eu.cokeman.cycleareastats.mapper.level;

import eu.cokeman.cycleareastats.valueObject.*;
import org.mapstruct.Mapper;

@Mapper
public interface AdministrativeLevelCommonMapper {

  default AdministrativeLevelId mapToLevelId(Integer source) {
    return source == null ? null : new AdministrativeLevelId(source);
  }

  default Integer mapAdministrativeLevelIDToInteger(AdministrativeLevelId source) {
    return source != null ? source.value() : null;
  }

  default LevelName mapToLevelName(String source) {
    return new LevelName(source);
  }

  default String mapLevelNameToString(LevelName source) {
    return source.name();
  }

  default LevelOrder mapToLevelOrder(Integer source) {
    return new LevelOrder(source);
  }

  default Integer mapLevelOrderToInteger(LevelOrder source) {
    if (source == null) {
      return null;
    }
    return source.order();
  }
}
