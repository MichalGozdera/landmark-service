package eu.cokeman.cycleareastats.service.level;

import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DeleteAdministrativeLevelServiceTest {
  private AdministrativeLevelRepository levelRepository;
  private DeleteAdministrativeLevelService service;

  @BeforeEach
  void setUp() {
    levelRepository = Mockito.mock(AdministrativeLevelRepository.class);
    service = new DeleteAdministrativeLevelService(levelRepository);
  }

  @Test
  @DisplayName("Deletes administrative level by ID")
  void shouldDeleteLevelById() {
    AdministrativeLevelId id = new AdministrativeLevelId(1);
    Mockito.doNothing().when(levelRepository).deleteLevel(id);

    service.deleteAdministrativeLevel(id);

    Mockito.verify(levelRepository, Mockito.times(1)).deleteLevel(id);
  }
}
