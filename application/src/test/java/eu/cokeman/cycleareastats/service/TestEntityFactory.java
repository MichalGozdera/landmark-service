package eu.cokeman.cycleareastats.service;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.entity.Country;
import eu.cokeman.cycleareastats.valueObject.*;
import java.time.Instant;

public class TestEntityFactory {
  public static Country country(Integer id, String name) {
    return Country.builder()
        .id(id == null ? null : new CountryId(id))
        .name(name)
        .createTime(Instant.now())
        .updateTime(Instant.now())
        .build();
  }

  public static AdministrativeLevel level(Integer id, Country country, int order, String name) {
    return AdministrativeLevel.builder()
        .id(id == null ? null : new AdministrativeLevelId(id))
        .country(country)
        .order(new LevelOrder(order))
        .name(new LevelName(name))
        .createTime(Instant.now())
        .updateTime(Instant.now())
        .build();
  }

  public static AdministrativeArea area(
      Integer id, String name, Country country, AdministrativeLevel level) {
    return AdministrativeArea.builder()
        .id(id == null ? null : new AdministrativeAreaId(id))
        .name(new AreaName(name))
        .level(level)
        .geometry("GEOMETRY") // Serializable value object
        .metadata(new LandmarkMetadata(new java.util.HashMap<>()))
        .createTime(Instant.now())
        .updateTime(Instant.now())
        .build();
  }
}
