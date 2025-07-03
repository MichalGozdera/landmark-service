package eu.cokeman.cycleareastats.entity;

import static org.junit.jupiter.api.Assertions.*;

import eu.cokeman.cycleareastats.valueObject.CountryId;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class CountryTest {
  @Test
  void builderShouldCreateObjectWithGivenValues() {
    Country country =
        Country.builder()
            .id(new CountryId(1))
            .name("Poland")
            .createTime(Instant.now())
            .updateTime(Instant.now())
            .build();
    assertNotNull(country);
    assertEquals("Poland", country.getName());
  }

  @Test
  void builderShouldThrowExceptionWhenNameIsNull() {
    assertThrows(
        NullPointerException.class,
        () ->
            Country.builder()
                .id(new CountryId(1))
                .name(null)
                .createTime(Instant.now())
                .updateTime(Instant.now())
                .build());
  }
}
