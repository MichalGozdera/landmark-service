package eu.cokeman.cycleareastats.service.area;

import static org.mockito.Mockito.*;

import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeleteAdministrativeAreaServiceTest {
  private AdministrativeAreaDomainService domainService;
  private DeleteAdministrativeAreaService service;

  @BeforeEach
  void setUp() {
    domainService = mock(AdministrativeAreaDomainService.class);
    service = new DeleteAdministrativeAreaService(domainService);
  }

  @Test
  void shouldDelegateToDomainService() {
    AdministrativeAreaId id = new AdministrativeAreaId(1);
    service.deleteAdministrativeArea(id);
    verify(domainService, times(1)).deleteAdministrativeArea(id);
  }
}
