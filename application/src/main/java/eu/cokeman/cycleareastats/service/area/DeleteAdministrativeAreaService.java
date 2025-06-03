package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.port.in.administrativearea.DeleteAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;

public class DeleteAdministrativeAreaService implements DeleteAdministrativeAreaUseCase {
    private final AdministrativeAreaRepository administrativeAreaRepository;

    public DeleteAdministrativeAreaService(AdministrativeAreaRepository administrativeAreaRepository) {
        this.administrativeAreaRepository = administrativeAreaRepository;
    }

    @Override
    public void deleteAdministrativeArea(AdministrativeAreaId administrativeAreaId) {
        administrativeAreaRepository.deleteAdministrativeArea(administrativeAreaId);
    }
}
