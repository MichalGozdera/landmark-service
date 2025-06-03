package eu.cokeman.cycleareastats.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeLevelDto;
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
 * AdministrativeAreaDto
 */

@JsonTypeName("AdministrativeArea")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AdministrativeAreaDto {

  private String id = null;

  private String name = null;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Instant createTime;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Instant updateTime;

  private AdministrativeLevelDto level;

  private String parent = null;

  private org.springframework.core.io.Resource geometry = null;

  private String geometrySimplified;

  public AdministrativeAreaDto id(String id) {
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

  public AdministrativeAreaDto name(String name) {
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

  public AdministrativeAreaDto createTime(Instant createTime) {
    this.createTime = createTime;
    return this;
  }

  /**
   * Get createTime
   * @return createTime
  */
  @Valid 
  @Schema(name = "createTime", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("createTime")
  public Instant getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Instant createTime) {
    this.createTime = createTime;
  }

  public AdministrativeAreaDto updateTime(Instant updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  /**
   * Get updateTime
   * @return updateTime
  */
  @Valid 
  @Schema(name = "updateTime", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("updateTime")
  public Instant getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Instant updateTime) {
    this.updateTime = updateTime;
  }

  public AdministrativeAreaDto level(AdministrativeLevelDto level) {
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
  public AdministrativeLevelDto getLevel() {
    return level;
  }

  public void setLevel(AdministrativeLevelDto level) {
    this.level = level;
  }

  public AdministrativeAreaDto parent(String parent) {
    this.parent = parent;
    return this;
  }

  /**
   * Get parent
   * @return parent
  */
  
  @Schema(name = "parent", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("parent")
  public String getParent() {
    return parent;
  }

  public void setParent(String parent) {
    this.parent = parent;
  }

  public AdministrativeAreaDto geometry(org.springframework.core.io.Resource geometry) {
    this.geometry = geometry;
    return this;
  }

  /**
   * Get geometry
   * @return geometry
  */
  @Valid 
  @Schema(name = "geometry", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("geometry")
  public org.springframework.core.io.Resource getGeometry() {
    return geometry;
  }

  public void setGeometry(org.springframework.core.io.Resource geometry) {
    this.geometry = geometry;
  }

  public AdministrativeAreaDto geometrySimplified(String geometrySimplified) {
    this.geometrySimplified = geometrySimplified;
    return this;
  }

  /**
   * Get geometrySimplified
   * @return geometrySimplified
  */
  
  @Schema(name = "geometrySimplified", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("geometrySimplified")
  public String getGeometrySimplified() {
    return geometrySimplified;
  }

  public void setGeometrySimplified(String geometrySimplified) {
    this.geometrySimplified = geometrySimplified;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdministrativeAreaDto administrativeArea = (AdministrativeAreaDto) o;
    return Objects.equals(this.id, administrativeArea.id) &&
        Objects.equals(this.name, administrativeArea.name) &&
        Objects.equals(this.createTime, administrativeArea.createTime) &&
        Objects.equals(this.updateTime, administrativeArea.updateTime) &&
        Objects.equals(this.level, administrativeArea.level) &&
        Objects.equals(this.parent, administrativeArea.parent) &&
        Objects.equals(this.geometry, administrativeArea.geometry) &&
        Objects.equals(this.geometrySimplified, administrativeArea.geometrySimplified);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, createTime, updateTime, level, parent, geometry, geometrySimplified);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdministrativeAreaDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
    sb.append("    level: ").append(toIndentedString(level)).append("\n");
    sb.append("    parent: ").append(toIndentedString(parent)).append("\n");
    sb.append("    geometry: ").append(toIndentedString(geometry)).append("\n");
    sb.append("    geometrySimplified: ").append(toIndentedString(geometrySimplified)).append("\n");
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

