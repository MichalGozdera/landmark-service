package eu.cokeman.cycleareastats.entity;

import static org.junit.jupiter.api.Assertions.*;

import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.AreaName;
import eu.cokeman.cycleareastats.valueObject.LandmarkMetadata;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class AdministrativeAreaTest {
  @Test
  void builderShouldCreateObjectWithGivenValues() {
    AdministrativeArea area =
        AdministrativeArea.builder()
            .name(new AreaName("Test Area"))
            .createTime(Instant.now())
            .updateTime(Instant.now())
            .build();
    assertNotNull(area);
    assertEquals("Test Area", area.getName().name());
  }

  @Test
  void builderShouldSetAllFieldsCorrectly() {
    AreaName name = new AreaName("Area");
    Country country =
        Country.builder()
            .id(null)
            .name("TestCountry")
            .createTime(Instant.now())
            .updateTime(Instant.now())
            .build();
    AdministrativeLevel level =
        AdministrativeLevel.builder()
            .id(null)
            .country(country)
            .order(new eu.cokeman.cycleareastats.valueObject.LevelOrder(1))
            .name(new eu.cokeman.cycleareastats.valueObject.LevelName("CITY"))
            .createTime(Instant.now())
            .updateTime(Instant.now())
            .build();
    String geometry = "geometry-data";
    java.util.HashMap<String, Object> metaMap = new java.util.HashMap<>();
    metaMap.put("desc", "desc");
    metaMap.put("src", "src");
    LandmarkMetadata metadata = new LandmarkMetadata(metaMap);
    AdministrativeAreaId parent = new AdministrativeAreaId(123);
    Instant createTime = Instant.now();
    Instant updateTime = Instant.now();

    AdministrativeArea area =
        AdministrativeArea.builder()
            .name(name)
            .level(level)
            .geometry(geometry)
            .metadata(metadata)
            .parent(parent)
            .createTime(createTime)
            .updateTime(updateTime)
            .build();

    assertEquals(name, area.getName());
    assertEquals(level, area.getLevel());
    assertEquals(geometry, area.getGeometry());
    assertEquals(metadata, area.getMetadata());
    assertEquals(parent, area.getParent());
  }

  @Test
  void builderShouldThrowExceptionWhenAreaNameIsNull() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new eu.cokeman.cycleareastats.valueObject.AreaName(null));
  }

  @Test
  void builderShouldThrowExceptionWhenLevelNameIsNull() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new eu.cokeman.cycleareastats.valueObject.LevelName(null));
  }

  @Test
  void builderShouldThrowExceptionWhenLevelOrderIsNull() {
    assertThrows(
        IllegalArgumentException.class,
        () -> new eu.cokeman.cycleareastats.valueObject.LevelOrder(null));
  }
}
