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

  private String id = null;

  private String name = null;

  private String geometryType = null;

  private String category = null;

  private String country = null;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Instant loadTime;

  private com.fasterxml.jackson.databind.JsonNode metadata;

  public LandmarkDto id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LandmarkDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @NotNull 
  @Schema(name = "name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LandmarkDto geometryType(String geometryType) {
    this.geometryType = geometryType;
    return this;
  }

  /**
   * Get geometryType
   * @return geometryType
  */
  @NotNull 
  @Schema(name = "geometryType", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("geometryType")
  public String getGeometryType() {
    return geometryType;
  }

  public void setGeometryType(String geometryType) {
    this.geometryType = geometryType;
  }

  public LandmarkDto category(String category) {
    this.category = category;
    return this;
  }

  /**
   * Get category
   * @return category
  */
  @NotNull 
  @Schema(name = "category", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("category")
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public LandmarkDto country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * @return country
  */
  
  @Schema(name = "country", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("country")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
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

  public LandmarkDto metadata(com.fasterxml.jackson.databind.JsonNode metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Get metadata
   * @return metadata
  */
  @Valid 
  @Schema(name = "metadata", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("metadata")
  public com.fasterxml.jackson.databind.JsonNode getMetadata() {
    return metadata;
  }

  public void setMetadata(com.fasterxml.jackson.databind.JsonNode metadata) {
    this.metadata = metadata;
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
        Objects.equals(this.loadTime, landmark.loadTime) &&
        Objects.equals(this.metadata, landmark.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, geometryType, category, country, loadTime, metadata);
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
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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

