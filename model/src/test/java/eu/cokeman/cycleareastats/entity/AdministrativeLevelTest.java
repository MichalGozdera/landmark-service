package eu.cokeman.cycleareastats.entity;

import static org.junit.jupiter.api.Assertions.*;

import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;
import eu.cokeman.cycleareastats.valueObject.LevelName;
import eu.cokeman.cycleareastats.valueObject.LevelOrder;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class AdministrativeLevelTest {
  @Test
  void builderShouldCreateObjectWithGivenValues() {
    Country country =
        Country.builder()
            .id(null)
            .name("TestCountry")
            .createTime(Instant.now())
            .updateTime(Instant.now())
            .build();
    AdministrativeLevel level =
        AdministrativeLevel.builder()
            .id(new AdministrativeLevelId(1))
            .country(country)
            .order(new LevelOrder(1))
            .name(new LevelName("CITY"))
            .createTime(Instant.now())
            .updateTime(Instant.now())
            .build();
    assertNotNull(level);
    assertEquals("CITY", level.getName().name());
    assertEquals(1, level.getOrder().order());
    assertEquals(country, level.getCountry());
  }

  @Test
  void builderShouldThrowExceptionWhenLevelNameIsNull() {
    assertThrows(IllegalArgumentException.class, () -> new LevelName(null));
  }

  @Test
  void builderShouldThrowExceptionWhenLevelOrderIsNull() {
    assertThrows(IllegalArgumentException.class, () -> new LevelOrder(null));
  }
}
