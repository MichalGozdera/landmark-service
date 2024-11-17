package eu.cokeman.cycleareastats.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.LandmarkEntity;
import eu.cokeman.cycleareastats.valueObject.LandmarkMetadata;
import org.locationtech.jts.geom.Geometry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;

@Mapper
public interface LandmarkJpaMapper extends LandmarkCommonMapper {

    public static LandmarkJpaMapper INSTANCE = Mappers.getMapper(LandmarkJpaMapper.class);

    @Mapping(target = "geometrytype", source = "geometryType")
    LandmarkEntity mapToJpa (Landmark landmark);

    @Mapping(target = "geometryType", source = "geometrytype")
    Landmark.Builder mapJpaToInternal (LandmarkEntity landmarkEntity);


    default String convertLandmarkMetadataToJpa(LandmarkMetadata metadata) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
       return mapper.writeValueAsString(metadata);
    }

    default LandmarkMetadata convertJpaToLandmarkMetadata(String source) throws JsonProcessingException {
       return new ObjectMapper().readValue(source, LandmarkMetadata.class);
    }

    default Geometry convertJpaToLandmarkMetadata(Serializable source) throws JsonProcessingException {
        return (Geometry) source;
    }

}
