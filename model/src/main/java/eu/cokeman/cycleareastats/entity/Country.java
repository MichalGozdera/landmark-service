package eu.cokeman.cycleareastats.entity;

import eu.cokeman.cycleareastats.valueObject.CountryId;
import java.util.Objects;

public class Country extends BaseEntity<CountryId> {
  private String name;

  private Country(Builder builder) {
    super.setId(builder.id);
    super.setCreateTime(builder.createTime);
    super.setUpdateTime(builder.updateTime);
    this.name = builder.name;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Builder toBuilder() {
    return builder()
        .id(this.getId())
        .name(this.name)
        .createTime(this.getCreateTime())
        .updateTime(this.getUpdateTime());
  }

  public static final class Builder {
    private CountryId id;
    private String name;
    private java.time.Instant createTime;
    private java.time.Instant updateTime;

    public Builder id(CountryId id) {
      this.id = id;
      return this;
    }

    public Builder name(String name) {
      this.name = Objects.requireNonNull(name, "Country name cannot be null");
      return this;
    }

    public Builder createTime(java.time.Instant createTime) {
      this.createTime = createTime;
      return this;
    }

    public Builder updateTime(java.time.Instant updateTime) {
      this.updateTime = updateTime;
      return this;
    }

    public Country build() {
      return new Country(this);
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = Objects.requireNonNull(name, "Country name cannot be null");
  }
}
