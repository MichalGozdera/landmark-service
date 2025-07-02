package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.port.in.administrativearea.AdministrativeAreaConverter;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaGeometry;
import eu.cokeman.cycleareastats.valueObject.LandmarkMetadata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

class ImportAdministrativeAreaServiceTest {
    private AdministrativeAreaConverter converter;
    private AdministrativeAreaDomainService domainService;
    private ImportAdministrativeAreaService service;

    @BeforeEach
    void setUp() {
        converter = mock(AdministrativeAreaConverter.class);
        domainService = mock(AdministrativeAreaDomainService.class);
        service = new ImportAdministrativeAreaService(converter, domainService);
    }

    @Test
    void shouldImportAllConvertedGeometries() {
        // Arrange: create real value objects and entities
        Country country = Country.builder().id(null).name("Polska").createTime(java.time.Instant.now()).updateTime(java.time.Instant.now()).build();
        AdministrativeLevel level = AdministrativeLevel.Builder.builder()
                .id(null)
                .country(country)
                .order(new eu.cokeman.cycleareastats.valueObject.LevelOrder(1))
                .name(new eu.cokeman.cycleareastats.valueObject.LevelName("CITY"))
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        LandmarkMetadata metadata = new LandmarkMetadata(new java.util.HashMap<>());
        Object geometry = new Object();
        AdministrativeAreaGeometry geom1 = new AdministrativeAreaGeometry("Area1", "geom1");
        AdministrativeAreaGeometry geom2 = new AdministrativeAreaGeometry("Area2", "geom2");
        when(converter.convertToLandmarksGeometries(geometry)).thenReturn(Set.of(geom1, geom2));

        // Act
        service.importAdministrativeAreas(level, metadata, geometry);

        // Assert
        verify(converter, times(1)).convertToLandmarksGeometries(geometry);
        verify(domainService, times(2)).createAdministrativeArea(any(AdministrativeArea.class));
    }
}
