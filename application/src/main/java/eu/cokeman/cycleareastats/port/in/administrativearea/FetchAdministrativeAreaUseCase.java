package eu.cokeman.cycleareastats.port.in.administrativearea;


import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;


public interface FetchAdministrativeAreaUseCase {

    AdministrativeArea findArea(AdministrativeAreaId administrativeAreaId);
}
