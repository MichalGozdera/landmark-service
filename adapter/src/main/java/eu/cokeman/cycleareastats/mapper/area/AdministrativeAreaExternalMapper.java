package eu.cokeman.cycleareastats.mapper.area;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.mapper.level.AdministrativeLevelExternalMapper;
import eu.cokeman.cycleareastats.openapi.model.*;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeLevelEntity;
import eu.cokeman.cycleareastats.valueObject.LandmarkMetadata;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.geojson.GeoJsonReader;
import org.locationtech.jts.io.geojson.GeoJsonWriter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface AdministrativeAreaExternalMapper extends AdministrativeAreaCommonMapper {

    public static AdministrativeAreaExternalMapper INSTANCE = Mappers.getMapper(AdministrativeAreaExternalMapper.class);

    AdministrativeArea.Builder mapToInternal(AdministrativeAreaRequestDto areasDto);

    AdministrativeAreaResponseDto mapToExternal(AdministrativeArea administrativeArea);


    default AdministrativeLevel mapLevelToInternal(AdministrativeLevelBasicDto level) {
        if (level == null) {
            return null;
        }
        return AdministrativeLevelExternalMapper.INSTANCE.mapLevelBasicToInternal(level).build();
    }


    default AdministrativeLevelDto mapLevelToExternal(AdministrativeLevel level) {
        if (level == null) {
            return null;
        }
        return AdministrativeLevelExternalMapper.INSTANCE.mapToExternal(level);
    }

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
