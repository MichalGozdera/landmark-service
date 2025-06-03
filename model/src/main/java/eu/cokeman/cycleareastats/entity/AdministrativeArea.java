package eu.cokeman.cycleareastats.entity;


import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import eu.cokeman.cycleareastats.valueObject.*;


public class AdministrativeArea extends AggregateRoot<AdministrativeAreaId> {

    private AreaName name;
    private AdministrativeLevel level;
    private Serializable geometry;
    private LandmarkMetadata metadata;
    private AdministrativeAreaId parent;
    private List<String> geometriesSimplified;

    private AdministrativeArea(Builder builder) {
        super.setId(builder.id);
        super.setCreateTime(builder.createTime);
        super.setUpdateTime(builder.updateTime);
        name = builder.name;
        level = builder.level;
        geometry = builder.geometry;
        metadata = builder.metadata;
        parent = builder.parent;
        geometriesSimplified = builder.geometriesSimplified;
    }


    public Builder toBuilder() {
        return Builder.builder()
                .id(this.getId())
                .name(this.name)
                .level(this.level)
                .geometry(this.geometry)
                .metadata(this.metadata)
                .parent(this.parent)
                .createTime(this.getCreateTime())
                .updateTime(this.getUpdateTime())
                .geometriesSimplified(this.getGeometriesSimplified());
    }


    public void initLandmark() {
        this.setId(new AdministrativeAreaId(UUID.randomUUID()));
    }


    public static final class Builder {
        private AdministrativeAreaId id;
        private AreaName name;
        private AdministrativeLevel level;
        private Serializable geometry;
        private LandmarkMetadata metadata;
        private AdministrativeAreaId parent;
        private Instant createTime;
        private Instant updateTime;
        private List<String> geometriesSimplified;

        public Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(AdministrativeAreaId val) {
            id = val;
            return this;
        }

        public Builder name(AreaName val) {
            name = val;
            return this;
        }

        public Builder level(AdministrativeLevel val) {
            level = val;
            return this;
        }

        public Builder geometry(Serializable val) {
            geometry = val;
            return this;
        }

        public Builder metadata(LandmarkMetadata val) {
            metadata = val;
            return this;
        }

        public Builder parent(AdministrativeAreaId val) {
            parent = val;
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

        public Builder geometriesSimplified(List<String> val) {
            geometriesSimplified = val;
            return this;
        }


        public AdministrativeArea build() {
            return new AdministrativeArea(this);
        }
    }

    public AreaName getName() {
        return name;
    }

    public AdministrativeLevel getLevel() {
        return level;
    }

    public Serializable getGeometry() {
        return geometry;
    }

    public List<String> getGeometriesSimplified() {
        return geometriesSimplified;
    }

    public LandmarkMetadata getMetadata() {
        return metadata;
    }

    public AdministrativeAreaId getParent() {
        return parent;
    }
}
