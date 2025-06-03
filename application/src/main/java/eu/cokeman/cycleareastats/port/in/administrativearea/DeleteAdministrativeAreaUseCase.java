package eu.cokeman.cycleareastats.port.in.administrativearea;

import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;

public interface DeleteAdministrativeAreaUseCase {
    void deleteAdministrativeArea(AdministrativeAreaId administrativeAreaId);
}
