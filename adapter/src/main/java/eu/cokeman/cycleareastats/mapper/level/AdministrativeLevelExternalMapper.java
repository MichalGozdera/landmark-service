package eu.cokeman.cycleareastats.mapper.level;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeLevelDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdministrativeLevelExternalMapper extends AdministrativeLevelCommonMapper {

    public static AdministrativeLevelExternalMapper INSTANCE = Mappers.getMapper(AdministrativeLevelExternalMapper.class);

    AdministrativeLevel.Builder mapToInternal(AdministrativeLevelDto levelDto);

    AdministrativeLevelDto mapToExternal(AdministrativeLevel level);

}
