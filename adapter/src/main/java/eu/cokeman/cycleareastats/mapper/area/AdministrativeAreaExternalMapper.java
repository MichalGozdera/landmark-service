package eu.cokeman.cycleareastats.mapper.area;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.events.AdministrativeAreaEvent;
import eu.cokeman.cycleareastats.mapper.level.AdministrativeLevelExternalMapper;
import eu.cokeman.cycleareastats.openapi.model.*;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaSimplifiedGeometry;
import eu.cokeman.cycleareastats.valueObject.EntityEventType;
import eu.cokeman.cycleareastats.valueObject.LandmarkMetadata;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.locationtech.jts.io.geojson.GeoJsonWriter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdministrativeAreaExternalMapper extends AdministrativeAreaCommonMapper {

  public static AdministrativeAreaExternalMapper INSTANCE =
      Mappers.getMapper(AdministrativeAreaExternalMapper.class);

  @Mapping(source = "geometry", target = "geometry", qualifiedByName = "toInternalGeometry")
  AdministrativeArea.Builder mapToInternal(AdministrativeAreaRequestDto areasDto);

  AdministrativeAreaResponseDto mapToExternal(AdministrativeArea administrativeArea);

  default AdministrativeLevel mapLevelBasicToInternal(AdministrativeLevelBasicDto level) {
    if (level == null) {
      return null;
    }
    return AdministrativeLevelExternalMapper.INSTANCE.mapLevelBasicToInternal(level).build();
  }

  default AdministrativeLevel mapLevelToInternal(AdministrativeLevelDto level) {
    if (level == null) {
      return null;
    }
    return AdministrativeLevelExternalMapper.INSTANCE.mapToInternal(level).build();
  }

  default AdministrativeLevelDto mapLevelToExternal(AdministrativeLevel level) {
    if (level == null) {
      return null;
    }
    return AdministrativeLevelExternalMapper.INSTANCE.mapToExternal(level);
  }

  AdministrativeAreaEventDto toMessaging(AdministrativeAreaEvent event);

  @Mapping(
      source = "operationType",
      target = "operationType",
      qualifiedByName = "fromMessagingEventType")
  AdministrativeAreaEvent.Builder fromMessaging(AdministrativeAreaEventDto event);

  @Named("toInternalGeometry")
  default Serializable toInternalGeometry(String external) {
    if (external == null) {
      return null;
    }
    GeoJsonReader reader = new GeoJsonReader();
    try {
      return reader.read(external);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  default String toExternalGeometry(Serializable internal) {
    if (internal == null) {
      return null;
    }
    GeoJsonWriter writer = new GeoJsonWriter();
    return writer.write((Geometry) internal);
  }

  default AdministrativeAreaSimplifiedGeometry fromMessagingGeometry(List<String> external) {
    return new AdministrativeAreaSimplifiedGeometry(external);
  }

  default List<String> toMessagingGeometry(AdministrativeAreaSimplifiedGeometry internal) {
    return internal == null ? null : internal.encodedLines();
  }

  @Named("fromMessagingEventType")
  default EntityEventType fromMessagingEventType(String eventType) {
    return EntityEventType.valueOf(eventType);
  }

  default String toMessagingEventType(EntityEventType eventType) {
    return eventType.name();
  }

  default LandmarkMetadata mapJsonToLandmarkMetadata(JsonNode source) {
    if (source == null) {
      return null;
    }
    ObjectMapper mapper = new ObjectMapper();
    HashMap map = mapper.convertValue(source, HashMap.class);
    return new LandmarkMetadata(map);
  }

  default JsonNode mapLandmarkMetadataToJson(LandmarkMetadata source) {
    if (source == null) {
      return null;
    }
    return new ObjectMapper().convertValue(source, JsonNode.class);
  }
}
