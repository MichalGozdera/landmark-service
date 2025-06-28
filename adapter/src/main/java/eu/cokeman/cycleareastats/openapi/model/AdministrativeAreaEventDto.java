package eu.cokeman.cycleareastats.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeAreaEventAreaDto;
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
 * AdministrativeAreaEventDto
 */

@JsonTypeName("AdministrativeAreaEvent")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AdministrativeAreaEventDto {

  private AdministrativeAreaEventAreaDto area;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Instant createdAt;

  private String operationType;

  public AdministrativeAreaEventDto area(AdministrativeAreaEventAreaDto area) {
    this.area = area;
    return this;
  }

  /**
   * Get area
   * @return area
  */
  @Valid 
  @Schema(name = "area", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("area")
  public AdministrativeAreaEventAreaDto getArea() {
    return area;
  }

  public void setArea(AdministrativeAreaEventAreaDto area) {
    this.area = area;
  }

  public AdministrativeAreaEventDto createdAt(Instant createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  */
  @Valid 
  @Schema(name = "createdAt", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("createdAt")
  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public AdministrativeAreaEventDto operationType(String operationType) {
    this.operationType = operationType;
    return this;
  }

  /**
   * Get operationType
   * @return operationType
  */
  
  @Schema(name = "operationType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("operationType")
  public String getOperationType() {
    return operationType;
  }

  public void setOperationType(String operationType) {
    this.operationType = operationType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdministrativeAreaEventDto administrativeAreaEvent = (AdministrativeAreaEventDto) o;
    return Objects.equals(this.area, administrativeAreaEvent.area) &&
        Objects.equals(this.createdAt, administrativeAreaEvent.createdAt) &&
        Objects.equals(this.operationType, administrativeAreaEvent.operationType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(area, createdAt, operationType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdministrativeAreaEventDto {\n");
    sb.append("    area: ").append(toIndentedString(area)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    operationType: ").append(toIndentedString(operationType)).append("\n");
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

