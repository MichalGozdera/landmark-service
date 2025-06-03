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

    @Mapping(target = "geometry", ignore = true)
    AdministrativeArea.Builder mapToInternal(AdministrativeAreaDto areasDto);

    @Mapping(target = "geometry", ignore = true)
    AdministrativeAreaDto mapToExternal(AdministrativeArea administrativeArea);


    default AdministrativeLevel mapLevelToInternal(AdministrativeLevelDto dto) {
        if (dto == null) {
            return null;
        }
        return AdministrativeLevelExternalMapper.INSTANCE.mapToInternal(dto).build();
    }

    default AdministrativeLevelDto mapLevelToExternal(AdministrativeLevel level) {
        if (level == null) {
            return null;
        }
        return AdministrativeLevelExternalMapper.INSTANCE.mapToExternal(level);
    }
    }
