package eu.cokeman.cycleareastats.mapper.level;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.out.persistence.jpa.entity.AdministrativeLevelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdministrativeLevelJpaMapper extends AdministrativeLevelCommonMapper {

    public static AdministrativeLevelJpaMapper INSTANCE = Mappers.getMapper(AdministrativeLevelJpaMapper.class);

    AdministrativeLevelEntity mapToJpa (AdministrativeLevel level);

    AdministrativeLevel.Builder mapJpaToInternal (AdministrativeLevelEntity levelEntity);


}
