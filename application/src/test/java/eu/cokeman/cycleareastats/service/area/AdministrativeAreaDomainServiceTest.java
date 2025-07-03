package eu.cokeman.cycleareastats.service.area;

import static eu.cokeman.cycleareastats.service.TestEntityFactory.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.events.AdministrativeAreaEvent;
import eu.cokeman.cycleareastats.exception.LevelNotFoundException;
import eu.cokeman.cycleareastats.port.in.administrativearea.PolylineEncoder;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.port.out.publishing.AdministrativeAreaPublisher;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaSimplifiedGeometry;
import eu.cokeman.cycleareastats.valueObject.LandmarkMetadata;
import eu.cokeman.cycleareastats.valueObject.LevelName;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

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
    service =
        new AdministrativeAreaDomainService(areaRepository, levelRepository, publisher, encoder);
  }

  @Test
  void findExistingLevelShouldReturnLevelIfExists() {
    Country country = country(null, "Poland");
    AdministrativeLevel level = level(null, country, 1, "CITY");
    AdministrativeArea area = area(null, "TestArea", country, level);
    AdministrativeLevel found = level(null, country, 1, "CITY");
    when(levelRepository.findByCountryAndName(country, new LevelName("CITY")))
        .thenReturn(Optional.of(found));

    AdministrativeLevel result = service.findExistingLevel(area);
    assertEquals(found, result);
  }

  @Test
  void findExistingLevelShouldThrowIfNotFound() {
    Country country = country(null, "Poland");
    AdministrativeLevel level = level(null, country, 1, "CITY");
    AdministrativeArea area = area(null, "TestArea", country, level);
    when(levelRepository.findByCountryAndName(country, new LevelName("CITY")))
        .thenReturn(Optional.empty());

    assertThrows(LevelNotFoundException.class, () -> service.findExistingLevel(area));
  }

  @Test
  void publishEventShouldDelegateToPublisher() throws InterruptedException {
    // Arrange: create real AdministrativeArea and event using TestEntityFactory
    Country country = country(null, "Poland");
    AdministrativeLevel level = level(null, country, 1, "CITY");
    LandmarkMetadata metadata = new LandmarkMetadata(new java.util.HashMap<>());
    AdministrativeArea area =
        area(null, "TestArea", country, level).toBuilder()
            .metadata(metadata)
            .geometry("geom")
            .build();
    when(levelRepository.findByCountryAndName(country, new LevelName("CITY")))
        .thenReturn(Optional.of(level));
    AdministrativeAreaSimplifiedGeometry simplifiedGeometry =
        new AdministrativeAreaSimplifiedGeometry(java.util.List.of("geom-simplified"));
    when(encoder.getGeometriesSimplified("geom")).thenReturn(simplifiedGeometry);

    // Act: call persist (which triggers async event publishing)
    AdministrativeAreaId id = service.createAdministrativeArea(area);

    // Wait for async event to be published
    Thread.sleep(200); // small wait for async thread

    // Assert: publisher was called with event containing correct area and simplified geometry
    ArgumentCaptor<AdministrativeAreaEvent> captor =
        ArgumentCaptor.forClass(AdministrativeAreaEvent.class);
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
    AdministrativeAreaId parentId = new AdministrativeAreaId(2);
    AdministrativeAreaId childId = new AdministrativeAreaId(3);
    Country country = country(null, "Poland");
    AdministrativeLevel level = level(null, country, 1, "CITY");
    LandmarkMetadata metadata = new LandmarkMetadata(new java.util.HashMap<>());
    AdministrativeArea child1 =
        area(null, "Child1", country, level).toBuilder()
            .parent(parentId)
            .metadata(metadata)
            .geometry("geom1")
            .build();
    AdministrativeArea child2 =
        area(childId.value(), "Child2", country, level).toBuilder()
            .metadata(metadata)
            .geometry("geom2")
            .build();
    // Mock repository to return parent and children
    when(areaRepository.findParent(childId)).thenReturn(parentId);
    when(areaRepository.findChildren(parentId)).thenReturn(List.of(child1, child2));
    // Mock levelRepository to avoid LevelNotFoundException
    when(levelRepository.findByCountryAndName(country, new LevelName("CITY")))
        .thenReturn(Optional.of(level));

    // Act: call the method under test
    AdministrativeAreaId result = service.findParentId(parentId);

    // Assert: parentId is returned and child2's parent is updated
    assertNull(result);
    assertEquals(parentId, child1.getParent());
    // Capture updateAdministrativeArea calls and check child2's parent
    ArgumentCaptor<AdministrativeArea> areaCaptor =
        ArgumentCaptor.forClass(AdministrativeArea.class);
    verify(areaRepository, atLeastOnce())
        .updateAdministrativeArea(eq(child2.getId()), areaCaptor.capture());
    boolean found =
        areaCaptor.getAllValues().stream()
            .anyMatch(a -> a.getName().equals(child2.getName()) && parentId.equals(a.getParent()));
    assertTrue(found, "child2 should be updated with parentId as its parent");
  }
}
