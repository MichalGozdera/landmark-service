package eu.cokeman.cycleareastats.service.area;

import static eu.cokeman.cycleareastats.service.TestEntityFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateAdministrativeAreaServiceTest {
  private AdministrativeAreaDomainService domainService;
  private CreateAdministrativeAreaService service;

  @BeforeEach
  void setUp() {
    domainService = mock(AdministrativeAreaDomainService.class);
    service = new CreateAdministrativeAreaService(domainService);
  }

  @Test
  void shouldDelegateToDomainServiceAndReturnId() {
    // Arrange: create AdministrativeArea using TestEntityFactory
    var country = country(null, "Poland");
    var level = level(null, country, 1, "CITY");
    AdministrativeArea area = area(null, "TestArea", country, level);
    AdministrativeAreaId expectedId = new AdministrativeAreaId(42);
    when(domainService.createAdministrativeArea(area)).thenReturn(expectedId);

    // Act
    AdministrativeAreaId result = service.createAdministrativeArea(area);

    // Assert
    assertEquals(expectedId, result);
    verify(domainService, times(1)).createAdministrativeArea(area);
  }
}
