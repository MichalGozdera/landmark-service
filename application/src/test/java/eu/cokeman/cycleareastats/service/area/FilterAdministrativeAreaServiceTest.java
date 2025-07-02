package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilterAdministrativeAreaServiceTest {
    private AdministrativeAreaRepository repository;
    private FilterAdministrativeAreaService service;

    @BeforeEach
    void setUp() {
        repository = mock(AdministrativeAreaRepository.class);
        service = new FilterAdministrativeAreaService(repository);
    }

    @Test
    void shouldFindByLevelAndCountry() {
        AdministrativeArea area = AdministrativeArea.builder()
                .name(new eu.cokeman.cycleareastats.valueObject.AreaName("Area1"))
                .geometry("geom1")
                .metadata(new eu.cokeman.cycleareastats.valueObject.LandmarkMetadata(new HashMap<>()))
                .level(null)
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        when(repository.findByLevelAndCountry("city", "Poland")).thenReturn(List.of(area));
        List<AdministrativeArea> result = service.findByLevelAndCountry("city", "Poland");
        assertEquals(1, result.size());
        verify(repository, times(1)).findByLevelAndCountry("city", "Poland");
    }

    @Test
    void shouldFindByMetadataContains() {
        AdministrativeArea area = AdministrativeArea.builder()
                .name(new eu.cokeman.cycleareastats.valueObject.AreaName("Area2"))
                .geometry("geom2")
                .metadata(new eu.cokeman.cycleareastats.valueObject.LandmarkMetadata(new HashMap<>()))
                .level(null)
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        when(repository.findByMetadataContains("desc")).thenReturn(List.of(area));
        List<AdministrativeArea> result = service.findByMetadataContains("desc");
        assertEquals(1, result.size());
        verify(repository, times(1)).findByMetadataContains("desc");
    }

    @Test
    void shouldFindSimpleByLevelAndCountry() {
        AdministrativeArea area = AdministrativeArea.builder()
                .name(new eu.cokeman.cycleareastats.valueObject.AreaName("Area3"))
                .geometry("geom3")
                .metadata(new eu.cokeman.cycleareastats.valueObject.LandmarkMetadata(new HashMap<>()))
                .level(null)
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        when(repository.findSimpleByLevelAndCountry("city", "Poland")).thenReturn(List.of(area));
        List<AdministrativeArea> result = service.findSimpleByLevelAndCountry("city", "Poland");
        assertEquals(1, result.size());
        verify(repository, times(1)).findSimpleByLevelAndCountry("city", "Poland");
    }

    @Test
    void shouldFindSimpleByMetadataContains() {
        AdministrativeArea area = AdministrativeArea.builder()
                .name(new eu.cokeman.cycleareastats.valueObject.AreaName("Area4"))
                .geometry("geom4")
                .metadata(new eu.cokeman.cycleareastats.valueObject.LandmarkMetadata(new HashMap<>()))
                .level(null)
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        when(repository.findSimpleByMetadataContains("desc")).thenReturn(List.of(area));
        List<AdministrativeArea> result = service.findSimpleByMetadataContains("desc");
        assertEquals(1, result.size());
        verify(repository, times(1)).findSimpleByMetadataContains("desc");
    }
}

