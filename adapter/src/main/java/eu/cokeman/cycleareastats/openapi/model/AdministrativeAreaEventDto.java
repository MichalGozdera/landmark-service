package eu.cokeman.cycleareastats.openapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.Instant;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.format.annotation.DateTimeFormat;

/** AdministrativeAreaEventDto */
@JsonTypeName("AdministrativeAreaEvent")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AdministrativeAreaEventDto {

  private AdministrativeAreaEventAreaDto area;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Instant createdAt;

  private String operationType;

  @Valid private List<String> simplifiedGeometry;

  public AdministrativeAreaEventDto area(AdministrativeAreaEventAreaDto area) {
    this.area = area;
    return this;
  }

  /**
   * Get area
   *
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
   *
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
   *
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

  public AdministrativeAreaEventDto simplifiedGeometry(List<String> simplifiedGeometry) {
    this.simplifiedGeometry = simplifiedGeometry;
    return this;
  }

  public AdministrativeAreaEventDto addSimplifiedGeometryItem(String simplifiedGeometryItem) {
    if (this.simplifiedGeometry == null) {
      this.simplifiedGeometry = new ArrayList<>();
    }
    this.simplifiedGeometry.add(simplifiedGeometryItem);
    return this;
  }

  /**
   * Get simplifiedGeometry
   *
   * @return simplifiedGeometry
   */
  @Schema(name = "simplifiedGeometry", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("simplifiedGeometry")
  public List<String> getSimplifiedGeometry() {
    return simplifiedGeometry;
  }

  public void setSimplifiedGeometry(List<String> simplifiedGeometry) {
    this.simplifiedGeometry = simplifiedGeometry;
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
    return Objects.equals(this.area, administrativeAreaEvent.area)
        && Objects.equals(this.createdAt, administrativeAreaEvent.createdAt)
        && Objects.equals(this.operationType, administrativeAreaEvent.operationType)
        && Objects.equals(this.simplifiedGeometry, administrativeAreaEvent.simplifiedGeometry);
  }

  @Override
  public int hashCode() {
    return Objects.hash(area, createdAt, operationType, simplifiedGeometry);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdministrativeAreaEventDto {\n");
    sb.append("    area: ").append(toIndentedString(area)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    operationType: ").append(toIndentedString(operationType)).append("\n");
    sb.append("    simplifiedGeometry: ").append(toIndentedString(simplifiedGeometry)).append("\n");
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
