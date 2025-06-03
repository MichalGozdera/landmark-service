package eu.cokeman.cycleareastats.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeLevelDto;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AdministrativeAreasImportRequestDto
 */

@JsonTypeName("AdministrativeAreasImportRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AdministrativeAreasImportRequestDto {

  private AdministrativeLevelDto level;

  public AdministrativeAreasImportRequestDto level(AdministrativeLevelDto level) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdministrativeAreasImportRequestDto administrativeAreasImportRequest = (AdministrativeAreasImportRequestDto) o;
    return Objects.equals(this.level, administrativeAreasImportRequest.level);
  }

  @Override
  public int hashCode() {
    return Objects.hash(level);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdministrativeAreasImportRequestDto {\n");
    sb.append("    level: ").append(toIndentedString(level)).append("\n");
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

