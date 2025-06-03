package eu.cokeman.cycleareastats.port.out.persistence;


import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.valueObject.Country;

import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;

import java.util.List;

public interface AdministrativeAreaRepository {

    AdministrativeArea findByAdministrativeAreaId(AdministrativeAreaId administrativeAreaId);

    AdministrativeArea updateAdministrativeArea(AdministrativeAreaId areaId, AdministrativeArea administrativeArea);

    void deleteAdministrativeArea(AdministrativeAreaId administrativeAreaId);

    List<AdministrativeArea> findByCountry(Country country);

    List<AdministrativeArea> filterAdministrativeAreas(String criteria);

    List<AdministrativeArea> findSubUnits(AdministrativeAreaId administrativeAreaId);

    AdministrativeAreaId importLandmark(AdministrativeArea administrativeArea);

    AdministrativeAreaId findParent(AdministrativeAreaId administrativeAreaId);
}
