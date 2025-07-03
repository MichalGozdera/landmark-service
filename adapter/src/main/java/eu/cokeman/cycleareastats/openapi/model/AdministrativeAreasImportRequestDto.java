package eu.cokeman.cycleareastats.openapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.*;
import java.util.Objects;

/** AdministrativeAreasImportRequestDto */
@JsonTypeName("AdministrativeAreasImportRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AdministrativeAreasImportRequestDto {

  private AdministrativeLevelBasicDto level;

  private com.fasterxml.jackson.databind.JsonNode metadata;

  public AdministrativeAreasImportRequestDto level(AdministrativeLevelBasicDto level) {
    this.level = level;
    return this;
  }

  /**
   * Get level
   *
   * @return level
   */
  @NotNull
  @Valid
  @Schema(name = "level", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("level")
  public AdministrativeLevelBasicDto getLevel() {
    return level;
  }

  public void setLevel(AdministrativeLevelBasicDto level) {
    this.level = level;
  }

  public AdministrativeAreasImportRequestDto metadata(
      com.fasterxml.jackson.databind.JsonNode metadata) {
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
    AdministrativeAreasImportRequestDto administrativeAreasImportRequest =
        (AdministrativeAreasImportRequestDto) o;
    return Objects.equals(this.level, administrativeAreasImportRequest.level)
        && Objects.equals(this.metadata, administrativeAreasImportRequest.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(level, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdministrativeAreasImportRequestDto {\n");
    sb.append("    level: ").append(toIndentedString(level)).append("\n");
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
