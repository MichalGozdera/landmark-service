package eu.cokeman.cycleareastats.port.out.persistence;


import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;
import eu.cokeman.cycleareastats.valueObject.Country;
import eu.cokeman.cycleareastats.valueObject.LevelName;

import java.util.List;
import java.util.Optional;

public interface AdministrativeLevelRepository {

    AdministrativeLevel findByAdministrativeLevelId(AdministrativeLevelId administrativeLevelId);

    AdministrativeLevel updateLevel(AdministrativeLevelId levelId, AdministrativeLevel level);

    void deleteLevel(AdministrativeLevelId administrativeLevelId);

    Optional<AdministrativeLevel> findByCountryAndName(Country country, LevelName name);

    List<AdministrativeLevel> filterAdministrativeLevels(String criteria);

    AdministrativeLevelId createLevel(AdministrativeLevel administrativeLevel);

}
