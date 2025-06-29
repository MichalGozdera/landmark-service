package eu.cokeman.cycleareastats.port.out.persistence;


import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.Country;

import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;

import java.io.Serializable;
import java.util.List;

public interface AdministrativeAreaRepository {

    AdministrativeArea findByAdministrativeAreaId(AdministrativeAreaId administrativeAreaId);

    AdministrativeArea findSimpleByAdministrativeAreaId(AdministrativeAreaId administrativeAreaId);

    AdministrativeArea updateAdministrativeArea(AdministrativeAreaId areaId, AdministrativeArea administrativeArea);

    void deleteAdministrativeArea(AdministrativeAreaId administrativeAreaId);

    AdministrativeAreaId createArea(AdministrativeArea administrativeArea);

    List<AdministrativeArea> findByLevelAndCountry(String levelName, String countryName);

    List<AdministrativeArea> findByMetadataContains(String metadataQuery);

    List<AdministrativeArea> findSimpleByLevelAndCountry(String levelName, String countryName);

    List<AdministrativeArea> findSimpleByMetadataContains(String metadataQuery);

    AdministrativeAreaId findParent(AdministrativeAreaId child);

    List<AdministrativeArea> findChildren(AdministrativeAreaId parent);
}
