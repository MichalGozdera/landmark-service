package eu.cokeman.cycleareastats.mapper.area;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.mapper.level.AdministrativeLevelJpaMapper;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeAreaEntity;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeLevelEntity;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.GeometrySimplifiedEntity;
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


    @Mapping(target = "uuid", source = "id")
    @Mapping(target = "geometriesSimplified", ignore = true)
    AdministrativeAreaEntity mapToJpa(AdministrativeArea administrativeArea);

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "geometriesSimplified", ignore = true)
    AdministrativeArea.Builder mapJpaToInternal(AdministrativeAreaEntity administrativeAreaEntity);

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

    default Geometry convertJpaToLandmarkMetadata(Serializable source) throws JsonProcessingException {
        return (Geometry) source;
    }

    default List<String> convertJpaGeometriesSimplifiedToInternal(List<GeometrySimplifiedEntity> jpa) {
        return jpa.stream().map(r -> r.getLine()).toList();
    }

    default List<GeometrySimplifiedEntity> convertInternalGeometriesSimplifiedToJpa(List<String> internal) {
        return internal.stream().map(r -> {
            var newEntity = new GeometrySimplifiedEntity();
            newEntity.setLine(r);
            return newEntity;
        }).toList();
    }

}
