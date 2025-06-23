package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.port.in.administrativearea.UpdateAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;

public class UpdateAdministrativeAreaService implements UpdateAdministrativeAreaUseCase {
    private final AdministrativeAreaRepository administrativeAreaRepository;
    private final AreaLevelBinder levelBinder;


    public UpdateAdministrativeAreaService(AdministrativeAreaRepository administrativeAreaRepository, AdministrativeLevelRepository levelRepository) {
        this.administrativeAreaRepository = administrativeAreaRepository;
        this.levelBinder = new AreaLevelBinder(levelRepository);
    }

    @Override
    public AdministrativeArea updateAdministrativeArea(AdministrativeAreaId areaId, AdministrativeArea administrativeArea) {
        administrativeArea = levelBinder.bindLevelId(administrativeArea.getLevel(), administrativeArea);
        return administrativeAreaRepository.updateAdministrativeArea(areaId, administrativeArea);
    }
}
