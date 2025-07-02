package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        // Arrange: create real AdministrativeArea
        AdministrativeAreaId id = new AdministrativeAreaId(1);
        AdministrativeArea area = AdministrativeArea.builder()
                .name(new eu.cokeman.cycleareastats.valueObject.AreaName("TestArea"))
                .geometry("geom")
                .metadata(new eu.cokeman.cycleareastats.valueObject.LandmarkMetadata(new java.util.HashMap<>()))
                .level(null)
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        AdministrativeArea expected = area;
        when(domainService.updateAdministrativeArea(id, area)).thenReturn(expected);

        // Act
        AdministrativeArea result = service.updateAdministrativeArea(id, area);

        // Assert
        assertEquals(expected, result);
        verify(domainService, times(1)).updateAdministrativeArea(id, area);
    }
}
