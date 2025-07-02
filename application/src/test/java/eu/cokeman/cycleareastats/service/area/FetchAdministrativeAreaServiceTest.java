package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FetchAdministrativeAreaServiceTest {
    private AdministrativeAreaRepository repository;
    private FetchAdministrativeAreaService service;

    @BeforeEach
    void setUp() {
        repository = mock(AdministrativeAreaRepository.class);
        service = new FetchAdministrativeAreaService(repository);
    }

    @Test
    void shouldDelegateFindAreaToRepository() {
        AdministrativeAreaId id = new AdministrativeAreaId(1);
        AdministrativeArea area = mock(AdministrativeArea.class);
        when(repository.findByAdministrativeAreaId(id)).thenReturn(area);
        assertEquals(area, service.findArea(id));
        verify(repository, times(1)).findByAdministrativeAreaId(id);
    }

    @Test
    void shouldDelegateFindSimpleAreaToRepository() {
        AdministrativeAreaId id = new AdministrativeAreaId(2);
        AdministrativeArea area = mock(AdministrativeArea.class);
        when(repository.findSimpleByAdministrativeAreaId(id)).thenReturn(area);
        assertEquals(area, service.findSimpleArea(id));
        verify(repository, times(1)).findSimpleByAdministrativeAreaId(id);
    }
}

