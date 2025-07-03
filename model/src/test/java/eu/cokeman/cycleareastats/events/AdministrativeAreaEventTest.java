package eu.cokeman.cycleareastats.events;

import static org.junit.jupiter.api.Assertions.*;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaSimplifiedGeometry;
import eu.cokeman.cycleareastats.valueObject.EntityEventType;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class AdministrativeAreaEventTest {
  @Test
  void builderShouldCreateObjectWithGivenValues() {
    AdministrativeArea area = org.mockito.Mockito.mock(AdministrativeArea.class);
    AdministrativeAreaSimplifiedGeometry simplifiedGeometry =
        org.mockito.Mockito.mock(AdministrativeAreaSimplifiedGeometry.class);
    EntityEventType type = EntityEventType.CREATED;
    AdministrativeAreaEvent event =
        AdministrativeAreaEvent.builder()
            .area(area)
            .simplifiedGeometry(simplifiedGeometry)
            .operationType(type)
            .build();
    assertNotNull(event);
    assertEquals(area, event.getArea());
    assertEquals(simplifiedGeometry, event.getSimplifiedGeometry());
    assertEquals(type, event.getOperationType());
    assertNotNull(event.getCreatedAt());
  }

  @Test
  void builderShouldAllowNullSimplifiedGeometry() {
    AdministrativeArea area = org.mockito.Mockito.mock(AdministrativeArea.class);
    EntityEventType type = EntityEventType.CREATED;
    AdministrativeAreaEvent event =
        AdministrativeAreaEvent.builder()
            .area(area)
            .simplifiedGeometry(null)
            .operationType(type)
            .build();
    assertNull(event.getSimplifiedGeometry());
  }

  @Test
  void builderShouldAllowNullArea() {
    AdministrativeAreaSimplifiedGeometry simplifiedGeometry =
        org.mockito.Mockito.mock(AdministrativeAreaSimplifiedGeometry.class);
    EntityEventType type = EntityEventType.CREATED;
    AdministrativeAreaEvent event =
        AdministrativeAreaEvent.builder()
            .area(null)
            .simplifiedGeometry(simplifiedGeometry)
            .operationType(type)
            .build();
    assertNull(event.getArea());
  }

  @Test
  void builderShouldAllowNullOperationType() {
    AdministrativeArea area = org.mockito.Mockito.mock(AdministrativeArea.class);
    AdministrativeAreaSimplifiedGeometry simplifiedGeometry =
        org.mockito.Mockito.mock(AdministrativeAreaSimplifiedGeometry.class);
    AdministrativeAreaEvent event =
        AdministrativeAreaEvent.builder()
            .area(area)
            .simplifiedGeometry(simplifiedGeometry)
            .operationType(null)
            .build();
    assertNull(event.getOperationType());
  }

  @Test
  void builderShouldSetCreatedAtToCurrentTime() {
    AdministrativeArea area = org.mockito.Mockito.mock(AdministrativeArea.class);
    AdministrativeAreaSimplifiedGeometry simplifiedGeometry =
        org.mockito.Mockito.mock(AdministrativeAreaSimplifiedGeometry.class);
    EntityEventType type = EntityEventType.CREATED;
    Instant before = Instant.now();
    AdministrativeAreaEvent event =
        AdministrativeAreaEvent.builder()
            .area(area)
            .simplifiedGeometry(simplifiedGeometry)
            .operationType(type)
            .build();
    Instant after = Instant.now();
    assertNotNull(event.getCreatedAt());
    assertFalse(event.getCreatedAt().isBefore(before));
    assertFalse(event.getCreatedAt().isAfter(after));
  }
}
