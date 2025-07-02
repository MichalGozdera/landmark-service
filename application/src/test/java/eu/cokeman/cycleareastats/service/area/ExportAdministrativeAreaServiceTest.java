package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.port.in.administrativearea.AdministrativeAreaConverter;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaGeometry;
import eu.cokeman.cycleareastats.valueObject.AreaName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExportAdministrativeAreaServiceTest {
    private AdministrativeAreaRepository repository;
    private AdministrativeAreaConverter converter;
    private ExportAdministrativeAreaService service;

    @BeforeEach
    void setUp() {
        repository = mock(AdministrativeAreaRepository.class);
        converter = mock(AdministrativeAreaConverter.class);
        service = new ExportAdministrativeAreaService(repository, converter);
    }

    @Test
    void shouldExportKmlByCountryAndLevel() {
        // Arrange: create real AdministrativeArea list
        AdministrativeArea area1 = AdministrativeArea.builder()
                .name(new AreaName("Area1"))
                .geometry("geom1")
                .metadata(new eu.cokeman.cycleareastats.valueObject.LandmarkMetadata(new HashMap<>()))
                .level(null)
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        AdministrativeArea area2 = AdministrativeArea.builder()
                .name(new AreaName("Area2"))
                .geometry("geom2")
                .metadata(new eu.cokeman.cycleareastats.valueObject.LandmarkMetadata(new HashMap<>()))
                .level(null)
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        List<AdministrativeArea> areas = List.of(area1, area2);
        when(repository.findByLevelAndCountry("city", "Poland")).thenReturn(areas);
        when(converter.convertToKml(anyList())).thenReturn("<kml>...</kml>");

        // Act
        String result = service.exportKmlByCountryAndLevel("Poland", "city");

        // Assert
        assertEquals("<kml>...</kml>", result);
        verify(repository, times(1)).findByLevelAndCountry("city", "Poland");
        verify(converter, times(1)).convertToKml(anyList());
    }
}

