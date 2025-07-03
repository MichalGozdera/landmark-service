package eu.cokeman.cycleareastats.service.area;

import static eu.cokeman.cycleareastats.service.TestEntityFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateAdministrativeAreaServiceTest {
  private AdministrativeAreaDomainService domainService;
  private UpdateAdministrativeAreaService service;

  @BeforeEach
  void setUp() {
    domainService = mock(AdministrativeAreaDomainService.class);
    service = new UpdateAdministrativeAreaService(domainService);
  }

  @Test
  void shouldDelegateToDomainServiceAndReturnArea() {
    // Arrange: create AdministrativeArea using TestEntityFactory
    var country = country(null, "Poland");
    var level = level(null, country, 1, "CITY");
    AdministrativeAreaId id = new AdministrativeAreaId(1);
    AdministrativeArea area = area(null, "TestArea", country, level);
    AdministrativeArea expected = area;
    when(domainService.updateAdministrativeArea(id, area)).thenReturn(expected);

    // Act
    AdministrativeArea result = service.updateAdministrativeArea(id, area);

    // Assert
    assertEquals(expected, result);
    verify(domainService, times(1)).updateAdministrativeArea(id, area);
  }
}
