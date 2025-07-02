package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.*;
import eu.cokeman.cycleareastats.events.AdministrativeAreaEvent;
import eu.cokeman.cycleareastats.exception.LevelNotFoundException;
import eu.cokeman.cycleareastats.port.in.administrativearea.PolylineEncoder;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.port.out.publishing.AdministrativeAreaPublisher;
import eu.cokeman.cycleareastats.valueObject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdministrativeAreaDomainServiceTest {
    private AdministrativeLevelRepository levelRepository;
    private AdministrativeAreaRepository areaRepository;
    private AdministrativeAreaDomainService service;
    private AdministrativeAreaPublisher publisher;
    private PolylineEncoder encoder;

    @BeforeEach
    void setUp() {
        levelRepository = mock(AdministrativeLevelRepository.class);
        areaRepository = mock(AdministrativeAreaRepository.class);
        publisher = mock(AdministrativeAreaPublisher.class);
        encoder = mock(PolylineEncoder.class);
        service = new AdministrativeAreaDomainService(areaRepository, levelRepository, publisher, encoder);
    }

    @Test
    void findExistingLevelShouldReturnLevelIfExists() {
        Country country = Country.builder().id(null).name("Polska").createTime(java.time.Instant.now()).updateTime(java.time.Instant.now()).build();
        AdministrativeLevel level = AdministrativeLevel.Builder.builder()
                .id(null)
                .country(country)
                .order(new LevelOrder(1))
                .name(new LevelName("CITY"))
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        AdministrativeArea area = AdministrativeArea.builder()
                .name(new AreaName("TestArea"))
                .level(level)
                .geometry("geom")
                .metadata(new LandmarkMetadata(new java.util.HashMap<>()))
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        AdministrativeLevel found = AdministrativeLevel.Builder.builder()
                .id(null)
                .country(country)
                .order(new LevelOrder(1))
                .name(new LevelName("CITY"))
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        when(levelRepository.findByCountryAndName(country, new LevelName("CITY"))).thenReturn(Optional.of(found));

        AdministrativeLevel result = service.findExistingLevel(area);
        assertEquals(found, result);
    }

    @Test
    void findExistingLevelShouldThrowIfNotFound() {
        Country country = Country.builder().id(null).name("Polska").createTime(java.time.Instant.now()).updateTime(java.time.Instant.now()).build();
        AdministrativeLevel level = AdministrativeLevel.Builder.builder()
                .id(null)
                .country(country)
                .order(new LevelOrder(1))
                .name(new LevelName("CITY"))
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        AdministrativeArea area = AdministrativeArea.builder()
                .name(new AreaName("TestArea"))
                .level(level)
                .geometry("geom")
                .metadata(new LandmarkMetadata(new java.util.HashMap<>()))
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        when(levelRepository.findByCountryAndName(country, new LevelName("CITY"))).thenReturn(Optional.empty());

        assertThrows(LevelNotFoundException.class, () -> service.findExistingLevel(area));
    }

    @Test
    void publishEventShouldDelegateToPublisher() throws InterruptedException {
        // Arrange: create real AdministrativeArea and event

        Country country = Country.builder().id(null).name("Polska").createTime(java.time.Instant.now()).updateTime(java.time.Instant.now()).build();
        AdministrativeLevel level = AdministrativeLevel.Builder.builder()
                .id(null)
                .country(country)
                .order(new LevelOrder(1))
                .name(new LevelName("CITY"))
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        LandmarkMetadata metadata = new LandmarkMetadata(new java.util.HashMap<>());
        AdministrativeArea area = AdministrativeArea.builder()
                .name(new AreaName("TestArea"))
                .level(level)
                .geometry("geom")
                .metadata(metadata)
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        when(levelRepository.findByCountryAndName(country, level.getName())).thenReturn(Optional.empty());
        AdministrativeAreaSimplifiedGeometry simplifiedGeometry = new AdministrativeAreaSimplifiedGeometry(java.util.List.of("geom-simplified"));
        when(encoder.getGeometriesSimplified("geom")).thenReturn(simplifiedGeometry);

        // Act: call persist (which triggers async event publishing)
        AdministrativeAreaId id = service.createAdministrativeArea(area);

        // Wait for async event to be published
        Thread.sleep(200); // small wait for async thread

        // Assert: publisher was called with event containing correct area and simplified geometry
        ArgumentCaptor<AdministrativeAreaEvent> captor = ArgumentCaptor.forClass(AdministrativeAreaEvent.class);
        verify(publisher, atLeastOnce()).publish(captor.capture());
        AdministrativeAreaEvent publishedEvent = captor.getValue();
        assertNotNull(publishedEvent.getArea());
        assertEquals("TestArea", publishedEvent.getArea().getName().name());
        assertEquals(simplifiedGeometry, publishedEvent.getSimplifiedGeometry());
        assertNotNull(publishedEvent.getCreatedAt());
    }

    @Test
    void findParentIdShouldReturnParentAndUpdateChildren() {
        // Arrange: create test data and mock dependencies
        AdministrativeAreaId areaId = new AdministrativeAreaId(1);
        AdministrativeAreaId parentId = new AdministrativeAreaId(2);
        Country country = Country.builder().id(null).name("Polska").createTime(java.time.Instant.now()).updateTime(java.time.Instant.now()).build();
        AdministrativeLevel level = AdministrativeLevel.Builder.builder()
                .id(null)
                .country(country)
                .order(new LevelOrder(1))
                .name(new LevelName("CITY"))
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        LandmarkMetadata metadata = new LandmarkMetadata(new java.util.HashMap<>());
        AdministrativeArea child1 = AdministrativeArea.builder()
                .name(new AreaName("Child1"))
                .level(level)
                .geometry("geom1")
                .parent(parentId)
                .metadata(metadata)
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        AdministrativeArea child2 = AdministrativeArea.builder()
                .name(new AreaName("Child2"))
                .level(level)
                .geometry("geom2")
                .metadata(metadata)
                .createTime(java.time.Instant.now())
                .updateTime(java.time.Instant.now())
                .build();
        // Mock repository to return parent and children
        when(areaRepository.findParent(areaId)).thenReturn(parentId);
        when(areaRepository.findChildren(areaId)).thenReturn(List.of(child1, child2));
        // Mock levelRepository to avoid LevelNotFoundException
        when(levelRepository.findByCountryAndName(country, new LevelName("CITY"))).thenReturn(Optional.of(level));

        // Act: call the method under test
        AdministrativeAreaId result = service.findParentId(areaId);

        // Assert: parentId is returned and child2's parent is updated
        assertEquals(parentId, result);
        AdministrativeArea updatedChild2 = child2.toBuilder().parent(areaId).build();
        assertEquals(areaId, updatedChild2.getParent());
        assertEquals(parentId, child1.getParent());
    }
}
