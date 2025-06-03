package eu.cokeman.cycleareastats.port.in.administrativearea;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;


public interface ImportAdministrativeAreaUseCase {

    void importAdministrativeAreas(AdministrativeLevel level, Object geometry);

    void processSubunit(AdministrativeAreaId administrativeAreaId);
}
