package eu.cokeman.cycleareastats.openapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.Instant;
import java.util.*;
import java.util.Objects;
import org.springframework.format.annotation.DateTimeFormat;

/** AdministrativeAreaResponseDto */
@JsonTypeName("AdministrativeAreaResponse")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AdministrativeAreaResponseDto {

  private Integer id = null;

  private String name = null;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Instant createTime;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Instant updateTime;

  private AdministrativeLevelDto level;

  private Integer parent = null;

  private String geometry;

  private com.fasterxml.jackson.databind.JsonNode metadata;

  public AdministrativeAreaResponseDto id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   *
   * @return id
   */
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public AdministrativeAreaResponseDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   *
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

  public AdministrativeAreaResponseDto createTime(Instant createTime) {
    this.createTime = createTime;
    return this;
  }

  /**
   * Get createTime
   *
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

  public AdministrativeAreaResponseDto updateTime(Instant updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  /**
   * Get updateTime
   *
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

  public AdministrativeAreaResponseDto level(AdministrativeLevelDto level) {
    this.level = level;
    return this;
  }

  /**
   * Get level
   *
   * @return level
   */
  @Valid
  @Schema(name = "level", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("level")
  public AdministrativeLevelDto getLevel() {
    return level;
  }

  public void setLevel(AdministrativeLevelDto level) {
    this.level = level;
  }

  public AdministrativeAreaResponseDto parent(Integer parent) {
    this.parent = parent;
    return this;
  }

  /**
   * Get parent
   *
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

  public AdministrativeAreaResponseDto geometry(String geometry) {
    this.geometry = geometry;
    return this;
  }

  /**
   * Get geometry
   *
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

  public AdministrativeAreaResponseDto metadata(com.fasterxml.jackson.databind.JsonNode metadata) {
    this.metadata = metadata;
    return this;
  }

  /**
   * Get metadata
   *
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
    AdministrativeAreaResponseDto administrativeAreaResponse = (AdministrativeAreaResponseDto) o;
    return Objects.equals(this.id, administrativeAreaResponse.id)
        && Objects.equals(this.name, administrativeAreaResponse.name)
        && Objects.equals(this.createTime, administrativeAreaResponse.createTime)
        && Objects.equals(this.updateTime, administrativeAreaResponse.updateTime)
        && Objects.equals(this.level, administrativeAreaResponse.level)
        && Objects.equals(this.parent, administrativeAreaResponse.parent)
        && Objects.equals(this.geometry, administrativeAreaResponse.geometry)
        && Objects.equals(this.metadata, administrativeAreaResponse.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, createTime, updateTime, level, parent, geometry, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdministrativeAreaResponseDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
    sb.append("    level: ").append(toIndentedString(level)).append("\n");
    sb.append("    parent: ").append(toIndentedString(parent)).append("\n");
    sb.append("    geometry: ").append(toIndentedString(geometry)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
