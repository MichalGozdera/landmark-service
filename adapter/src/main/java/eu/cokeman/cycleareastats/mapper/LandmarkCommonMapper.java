package eu.cokeman.cycleareastats.mapper;

import eu.cokeman.cycleareastats.valueObject.LandmarkId;
import eu.cokeman.cycleareastats.valueObject.LandmarkName;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper
public interface LandmarkCommonMapper {
    default LandmarkName mapToLandmarkName(String source) {
        return new LandmarkName(source);
    }

    default String mapLandmarkNameToString(LandmarkName source) {
        return source.name();
    }

    default LandmarkId mapToLandmarkid(UUID source) {
        return source == null ? null : new LandmarkId(source);
    }

    default UUID mapLandmarkIDToUUID(LandmarkId source) {
        return source != null ? source.value() : null;
    }
}
