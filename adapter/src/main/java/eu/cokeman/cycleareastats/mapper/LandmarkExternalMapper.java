package eu.cokeman.cycleareastats.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.openapi.model.LandmarkDto;
import eu.cokeman.cycleareastats.valueObject.LandmarkMetadata;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;

@Mapper
public interface LandmarkExternalMapper extends LandmarkCommonMapper {

    public static LandmarkExternalMapper INSTANCE = Mappers.getMapper(LandmarkExternalMapper.class);

    Landmark.Builder mapToInternal(LandmarkDto landmarkDto);

    LandmarkDto mapToExternal(Landmark landmark);


    default LandmarkMetadata mapJsonToLandmarkMetadata(JsonNode source) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap map = mapper.convertValue(source, HashMap.class);
        return new LandmarkMetadata(map);
    }

    default JsonNode mapLandmarkMetadataToJson(LandmarkMetadata source) throws JsonProcessingException {
        return new ObjectMapper().convertValue(source, JsonNode.class);
    }


}
