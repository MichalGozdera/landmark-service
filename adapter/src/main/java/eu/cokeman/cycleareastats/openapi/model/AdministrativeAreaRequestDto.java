package eu.cokeman.cycleareastats.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeLevelBasicDto;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AdministrativeAreaRequestDto
 */

@JsonTypeName("AdministrativeAreaRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AdministrativeAreaRequestDto {

  private String name = null;

  private AdministrativeLevelBasicDto level;

  private String geometry;

  private com.fasterxml.jackson.databind.JsonNode metadata;

  private Integer parent = null;

  public AdministrativeAreaRequestDto name(String name) {
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

  public AdministrativeAreaRequestDto level(AdministrativeLevelBasicDto level) {
    this.level = level;
    return this;
  }

  /**
   * Get level
   * @return level
  */
  @NotNull @Valid 
  @Schema(name = "level", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("level")
  public AdministrativeLevelBasicDto getLevel() {
    return level;
  }

  public void setLevel(AdministrativeLevelBasicDto level) {
    this.level = level;
  }

  public AdministrativeAreaRequestDto geometry(String geometry) {
    this.geometry = geometry;
    return this;
  }

  /**
   * Get geometry
   * @return geometry
  */
  
  @Schema(name = "geometry", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("geometry")
  public String getGeometry() {
    return geometry;
  }

  public void setGeometry(String geometry) {
    this.geometry = geometry;
  }

  public AdministrativeAreaRequestDto metadata(com.fasterxml.jackson.databind.JsonNode metadata) {
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

  public AdministrativeAreaRequestDto parent(Integer parent) {
    this.parent = parent;
    return this;
  }

  /**
   * Get parent
   * @return parent
  */
  
  @Schema(name = "parent", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("parent")
  public Integer getParent() {
    return parent;
  }

  public void setParent(Integer parent) {
    this.parent = parent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdministrativeAreaRequestDto administrativeAreaRequest = (AdministrativeAreaRequestDto) o;
    return Objects.equals(this.name, administrativeAreaRequest.name) &&
        Objects.equals(this.level, administrativeAreaRequest.level) &&
        Objects.equals(this.geometry, administrativeAreaRequest.geometry) &&
        Objects.equals(this.metadata, administrativeAreaRequest.metadata) &&
        Objects.equals(this.parent, administrativeAreaRequest.parent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, level, geometry, metadata, parent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdministrativeAreaRequestDto {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    level: ").append(toIndentedString(level)).append("\n");
    sb.append("    geometry: ").append(toIndentedString(geometry)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    parent: ").append(toIndentedString(parent)).append("\n");
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

