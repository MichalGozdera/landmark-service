package eu.cokeman.cycleareastats.mapper.area;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.mapper.level.AdministrativeLevelJpaMapper;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeAreaEntity;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeAreaSimpleEntity;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeLevelEntity;
import eu.cokeman.cycleareastats.valueObject.LandmarkMetadata;
import java.io.Serializable;
import org.locationtech.jts.geom.Geometry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdministrativeAreaJpaMapper extends AdministrativeAreaCommonMapper {

  public static AdministrativeAreaJpaMapper INSTANCE =
      Mappers.getMapper(AdministrativeAreaJpaMapper.class);

  AdministrativeAreaEntity mapToJpa(AdministrativeArea administrativeArea);

  AdministrativeArea.Builder mapJpaToInternal(AdministrativeAreaEntity administrativeAreaEntity);

  AdministrativeArea.Builder mapSimpleJpaToInternal(
      AdministrativeAreaSimpleEntity administrativeAreaEntity);

  default AdministrativeLevelEntity mapToJpa(AdministrativeLevel level) {
    return AdministrativeLevelJpaMapper.INSTANCE.mapToJpa(level);
  }

  default AdministrativeLevel mapLevelJpaToInternal(AdministrativeLevelEntity level) {
    if (level == null) {
      return null;
    }
    return AdministrativeLevelJpaMapper.INSTANCE.mapJpaToInternal(level).build();
  }

  default String convertLandmarkMetadataToJpa(LandmarkMetadata metadata)
      throws JsonProcessingException {
    if (metadata == null) {
      return null;
    }
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(metadata.metadata());
  }

  default LandmarkMetadata convertJpaToLandmarkMetadata(String source)
      throws JsonProcessingException {
    if (source == null) {
      return null;
    }
    ObjectMapper mapper = new ObjectMapper();
    var parsed =
        mapper.readValue(
            source,
            new com.fasterxml.jackson.core.type.TypeReference<
                java.util.HashMap<String, Object>>() {});
    return new LandmarkMetadata(parsed);
  }

  default Geometry convertJpaToJtsMode(Serializable source) {
    return (Geometry) source;
  }
}
