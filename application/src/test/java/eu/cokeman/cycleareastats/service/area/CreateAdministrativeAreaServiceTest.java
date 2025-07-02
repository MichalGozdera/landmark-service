package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        // Arrange: create real AdministrativeArea
        AdministrativeArea area = AdministrativeArea.builder()
                .name(new eu.cokeman.cycleareastats.valueObject.AreaName("TestArea"))
                .geometry("geom")
                .metadata(new eu.cokeman.cycleareastats.valueObject.LandmarkMetadata(new java.util.HashMap<>()))
                .level(null)
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        AdministrativeAreaId expectedId = new AdministrativeAreaId(42);
        when(domainService.createAdministrativeArea(area)).thenReturn(expectedId);

        // Act
        AdministrativeAreaId result = service.createAdministrativeArea(area);

        // Assert
        assertEquals(expectedId, result);
        verify(domainService, times(1)).createAdministrativeArea(area);
    }
}
