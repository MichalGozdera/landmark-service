package eu.cokeman.cycleareastats.service.level;

import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.port.in.administrativelevel.FetchAdministrativeLevelUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;

public class FetchAdministrativeLevelService implements FetchAdministrativeLevelUseCase {

  private final AdministrativeLevelRepository levelRepository;

  public FetchAdministrativeLevelService(AdministrativeLevelRepository levelRepository) {
    this.levelRepository = levelRepository;
  }

  @Override
  public AdministrativeLevel findLevel(AdministrativeLevelId administrativeLevelId) {
    return levelRepository.findByAdministrativeLevelId(administrativeLevelId);
  }
}
