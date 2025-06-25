package eu.cokeman.cycleareastats.openapi.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeAreaRequestDto;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CreateAdministrativeAreaRequestDto
 */

@JsonTypeName("createAdministrativeArea_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class CreateAdministrativeAreaRequestDto {

  private AdministrativeAreaRequestDto request;

  public CreateAdministrativeAreaRequestDto request(AdministrativeAreaRequestDto request) {
    this.request = request;
    return this;
  }

  /**
   * Get request
   * @return request
  */
  @Valid 
  @Schema(name = "request", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("request")
  public AdministrativeAreaRequestDto getRequest() {
    return request;
  }

  public void setRequest(AdministrativeAreaRequestDto request) {
    this.request = request;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateAdministrativeAreaRequestDto createAdministrativeAreaRequest = (CreateAdministrativeAreaRequestDto) o;
    return Objects.equals(this.request, createAdministrativeAreaRequest.request);
  }

  @Override
  public int hashCode() {
    return Objects.hash(request);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateAdministrativeAreaRequestDto {\n");
    sb.append("    request: ").append(toIndentedString(request)).append("\n");
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

