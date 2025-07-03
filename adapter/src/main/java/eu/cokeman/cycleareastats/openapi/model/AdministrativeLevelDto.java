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

/** AdministrativeLevelDto */
@JsonTypeName("AdministrativeLevel")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class AdministrativeLevelDto {

  private Integer id = null;

  private String name = null;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Instant createTime;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Instant updateTime;

  private String country = null;

  private Integer order = null;

  public AdministrativeLevelDto id(Integer id) {
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

  public AdministrativeLevelDto name(String name) {
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

  public AdministrativeLevelDto createTime(Instant createTime) {
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

  public AdministrativeLevelDto updateTime(Instant updateTime) {
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

  public AdministrativeLevelDto country(String country) {
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

  public AdministrativeLevelDto order(Integer order) {
    this.order = order;
    return this;
  }

  /**
   * Get order
   *
   * @return order
   */
  @NotNull
  @Schema(name = "order", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("order")
  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdministrativeLevelDto administrativeLevel = (AdministrativeLevelDto) o;
    return Objects.equals(this.id, administrativeLevel.id)
        && Objects.equals(this.name, administrativeLevel.name)
        && Objects.equals(this.createTime, administrativeLevel.createTime)
        && Objects.equals(this.updateTime, administrativeLevel.updateTime)
        && Objects.equals(this.country, administrativeLevel.country)
        && Objects.equals(this.order, administrativeLevel.order);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, createTime, updateTime, country, order);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdministrativeLevelDto {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    order: ").append(toIndentedString(order)).append("\n");
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
