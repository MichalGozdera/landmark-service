package eu.cokeman.cycleareastats.entity;

import eu.cokeman.cycleareastats.valueObject.AdministrativeLevelId;
import eu.cokeman.cycleareastats.valueObject.Country;
import eu.cokeman.cycleareastats.valueObject.LevelName;
import eu.cokeman.cycleareastats.valueObject.LevelOrder;

import java.time.Instant;

public class AdministrativeLevel extends BaseEntity<AdministrativeLevelId>{
    private Country country;
    private LevelOrder order;
    private LevelName name;

    private AdministrativeLevel(Builder builder) {
        super.setId(builder.id);
        super.setCreateTime(builder.createTime);
        super.setUpdateTime(builder.updateTime);
        country = builder.country;
        order = builder.order;
        name = builder.name;
    }

    public Builder toBuilder() {
        return Builder.builder()
                .id(this.getId())
                .createTime(this.getCreateTime())
                .updateTime(this.getUpdateTime())
                .country(this.country)
                .order(this.order)
                .name(this.name);
    }

    public static final class Builder {
        private AdministrativeLevelId id;
        private Country country;
        private LevelOrder order;
        private LevelName name;
        private Instant createTime;
        private Instant updateTime;

        public Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(AdministrativeLevelId val) {
            id = val;
            return this;
        }

        public Builder country(Country val) {
            country = val;
            return this;
        }

        public Builder order(LevelOrder val) {
            order = val;
            return this;
        }

        public Builder name(LevelName val) {
            name = val;
            return this;
        }
        public Builder createTime(Instant val) {
            createTime = val;
            return this;
        }

        public Builder updateTime(Instant val) {
            updateTime = val;
            return this;
        }

        public AdministrativeLevel build() {
            return new AdministrativeLevel(this);
        }
    }

    public Country getCountry() {
        return country;
    }

    public LevelOrder getOrder() {
        return order;
    }

    public LevelName getName() {
        return name;
    }
}
