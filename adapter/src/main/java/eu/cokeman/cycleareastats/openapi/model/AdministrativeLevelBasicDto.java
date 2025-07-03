package eu.cokeman.cycleareastats.openapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.*;
import java.util.*;
import java.util.Objects;

/** AdministrativeLevelBasicDto */
@JsonTypeName("AdministrativeLevelBasic")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AdministrativeLevelBasicDto {

  private String name = null;

  private String country = null;

  public AdministrativeLevelBasicDto name(String name) {
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

  public AdministrativeLevelBasicDto country(String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   *
   * @return country
   */
  @NotNull
  @Schema(name = "country", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("country")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdministrativeLevelBasicDto administrativeLevelBasic = (AdministrativeLevelBasicDto) o;
    return Objects.equals(this.name, administrativeLevelBasic.name)
        && Objects.equals(this.country, administrativeLevelBasic.country);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, country);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdministrativeLevelBasicDto {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
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
