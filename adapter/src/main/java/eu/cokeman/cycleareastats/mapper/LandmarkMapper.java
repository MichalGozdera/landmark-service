package eu.cokeman.cycleareastats.mapper;

import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.openapi.model.LandmarkDto;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.LandmarkEntity;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;
import eu.cokeman.cycleareastats.valueObject.LandmarkName;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface LandmarkMapper {

    public static LandmarkMapper INSTANCE = Mappers.getMapper(LandmarkMapper.class);

    Landmark.Builder mapToInternal(LandmarkDto landmarkDto);

    LandmarkDto mapToExternal (Landmark landmark);

    @Mapping(target = "geometrytype", source = "geometryType")
    LandmarkEntity mapToJpa (Landmark landmark);

    @Mapping(target = "geometryType", source = "geometrytype")
    Landmark.Builder mapJpaToInternal (LandmarkEntity landmarkEntity);

    default LandmarkName mapToLandmarkName(String source) {
       return new LandmarkName(source);
    }

    default String mapLandmarkNameToString(LandmarkName source) {
        return source.name();
    }

    default LandmarkId mapToLandmarkid(UUID source) {
        return new LandmarkId(source);
    }

    default UUID mapLandmarkIDToUUID(LandmarkId source) {
        return source.value();
    }

}
