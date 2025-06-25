package eu.cokeman.cycleareastats.port.in.administrativearea;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.LandmarkMetadata;


public interface CreateAdministrativeAreaUseCase {

    AdministrativeAreaId createAdministrativeArea(AdministrativeArea area);

    void processSubunit(AdministrativeAreaId administrativeAreaId);
}
