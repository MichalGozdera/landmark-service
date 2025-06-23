package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.exception.LevelNotFoundException;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;

public class AreaLevelBinder {

    private final AdministrativeLevelRepository levelRepository;

    public AreaLevelBinder(AdministrativeLevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }


    AdministrativeArea bindLevelId(AdministrativeLevel level, AdministrativeArea administrativeArea) {
        var matchingLevelId = levelRepository.findByCountryAndName(level.getCountry(), level.getName())
                .orElseThrow(() -> new LevelNotFoundException(
                        String.format("Level with name %s and country %s not found", level.getCountry().getName(), level.getName().name()))).getId();
        administrativeArea = administrativeArea.toBuilder()
                .level(administrativeArea.getLevel().toBuilder().id(matchingLevelId).build()).build();
        return administrativeArea;
    }
}
