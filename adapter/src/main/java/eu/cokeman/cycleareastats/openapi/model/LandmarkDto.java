package eu.cokeman.cycleareastats.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import java.time.Instant;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * LandmarkDto
 */

@JsonTypeName("Landmark")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class LandmarkDto {

  private eu.cokeman.cycleareastats.valueObject.LandmarkId id = null;

  private eu.cokeman.cycleareastats.valueObject.LandmarkName name = null;

  private eu.cokeman.cycleareastats.valueObject.LandmarkGeometryType geometryType = null;

  private eu.cokeman.cycleareastats.valueObject.LandmarkCategory category = null;

  private eu.cokeman.cycleareastats.valueObject.Country country = null;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Instant loadTime;

  public LandmarkDto id(eu.cokeman.cycleareastats.valueObject.LandmarkId id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @Valid 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public eu.cokeman.cycleareastats.valueObject.LandmarkId getId() {
    return id;
  }

  public void setId(eu.cokeman.cycleareastats.valueObject.LandmarkId id) {
    this.id = id;
  }

  public LandmarkDto name(eu.cokeman.cycleareastats.valueObject.LandmarkName name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @NotNull @Valid 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public eu.cokeman.cycleareastats.valueObject.LandmarkName getName() {
    return name;
  }

  public void setName(eu.cokeman.cycleareastats.valueObject.LandmarkName name) {
    this.name = name;
  }

  public LandmarkDto geometryType(eu.cokeman.cycleareastats.valueObject.LandmarkGeometryType geometryType) {
    this.geometryType = geometryType;
    return this;
  }

  /**
   * Get geometryType
   * @return geometryType
  */
  @NotNull @Valid 
  @Schema(name = "geometryType", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("geometryType")
  public eu.cokeman.cycleareastats.valueObject.LandmarkGeometryType getGeometryType() {
    return geometryType;
  }

  public void setGeometryType(eu.cokeman.cycleareastats.valueObject.LandmarkGeometryType geometryType) {
    this.geometryType = geometryType;
  }

  public LandmarkDto category(eu.cokeman.cycleareastats.valueObject.LandmarkCategory category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
  */
  @NotNull @Valid 
  @Schema(name = "category", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("category")
  public eu.cokeman.cycleareastats.valueObject.LandmarkCategory getCategory() {
    return category;
  }

  public void setCategory(eu.cokeman.cycleareastats.valueObject.LandmarkCategory category) {
    this.category = category;
  }

  public LandmarkDto country(eu.cokeman.cycleareastats.valueObject.Country country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * @return country
  */
  @Valid 
  @Schema(name = "country", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("country")
  public eu.cokeman.cycleareastats.valueObject.Country getCountry() {
    return country;
  }

  public void setCountry(eu.cokeman.cycleareastats.valueObject.Country country) {
    this.country = country;
  }

  public LandmarkDto loadTime(Instant loadTime) {
    this.loadTime = loadTime;
    return this;
  }

  /**
   * Get loadTime
   * @return loadTime
  */
  @Valid 
  @Schema(name = "loadTime", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("loadTime")
  public Instant getLoadTime() {
    return loadTime;
  }

  public void setLoadTime(Instant loadTime) {
    this.loadTime = loadTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LandmarkDto landmark = (LandmarkDto) o;
    return Objects.equals(this.id, landmark.id) &&
        Objects.equals(this.name, landmark.name) &&
        Objects.equals(this.geometryType, landmark.geometryType) &&
        Objects.equals(this.category, landmark.category) &&
        Objects.equals(this.country, landmark.country) &&
        Objects.equals(this.loadTime, landmark.loadTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, geometryType, category, country, loadTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LandmarkDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    geometryType: ").append(toIndentedString(geometryType)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    loadTime: ").append(toIndentedString(loadTime)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

