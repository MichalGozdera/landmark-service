package eu.cokeman.cycleareastats.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AdministrativeAreaSimpleDto
 */

@JsonTypeName("AdministrativeAreaSimple")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AdministrativeAreaSimpleDto {

  private Integer id;

  private String name;

  private com.fasterxml.jackson.databind.JsonNode metadata;

  private Integer parent;

  private Integer levelId;

  public AdministrativeAreaSimpleDto id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
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

  public AdministrativeAreaSimpleDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  
  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AdministrativeAreaSimpleDto metadata(com.fasterxml.jackson.databind.JsonNode metadata) {
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

  public AdministrativeAreaSimpleDto parent(Integer parent) {
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

  public AdministrativeAreaSimpleDto levelId(Integer levelId) {
    this.levelId = levelId;
    return this;
  }

  /**
   * Get levelId
   * @return levelId
  */
  
  @Schema(name = "levelId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("levelId")
  public Integer getLevelId() {
    return levelId;
  }

  public void setLevelId(Integer levelId) {
    this.levelId = levelId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdministrativeAreaSimpleDto administrativeAreaSimple = (AdministrativeAreaSimpleDto) o;
    return Objects.equals(this.id, administrativeAreaSimple.id) &&
        Objects.equals(this.name, administrativeAreaSimple.name) &&
        Objects.equals(this.metadata, administrativeAreaSimple.metadata) &&
        Objects.equals(this.parent, administrativeAreaSimple.parent) &&
        Objects.equals(this.levelId, administrativeAreaSimple.levelId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, metadata, parent, levelId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdministrativeAreaSimpleDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
    sb.append("    parent: ").append(toIndentedString(parent)).append("\n");
    sb.append("    levelId: ").append(toIndentedString(levelId)).append("\n");
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

