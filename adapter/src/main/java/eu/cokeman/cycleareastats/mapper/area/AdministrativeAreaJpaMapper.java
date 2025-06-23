package eu.cokeman.cycleareastats.mapper.area;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.mapper.level.AdministrativeLevelJpaMapper;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeAreaBaseEntity;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeAreaEntity;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeAreaSimpleEntity;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeLevelEntity;
import eu.cokeman.cycleareastats.valueObject.LandmarkMetadata;
import org.locationtech.jts.geom.Geometry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;
import java.util.List;

@Mapper
public interface AdministrativeAreaJpaMapper extends AdministrativeAreaCommonMapper {

    public static AdministrativeAreaJpaMapper INSTANCE = Mappers.getMapper(AdministrativeAreaJpaMapper.class);

    AdministrativeAreaEntity mapToJpa(AdministrativeArea administrativeArea);

    AdministrativeArea.Builder mapJpaToInternal(AdministrativeAreaBaseEntity administrativeAreaEntity);

    default AdministrativeLevelEntity mapToJpa(AdministrativeLevel level) {
        return AdministrativeLevelJpaMapper.INSTANCE.mapToJpa(level);
    }

    default AdministrativeLevel mapLevelJpaToInternal(AdministrativeLevelEntity level) {
        return AdministrativeLevelJpaMapper.INSTANCE.mapJpaToInternal(level).build();
    }

    default String convertLandmarkMetadataToJpa(LandmarkMetadata metadata) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(metadata);
    }

    default LandmarkMetadata convertJpaToLandmarkMetadata(String source) throws JsonProcessingException {
        return new ObjectMapper().readValue(source, LandmarkMetadata.class);
    }

    default Geometry convertJpaToJtsMode(Serializable source)  {
        return (Geometry) source;
    }

}
