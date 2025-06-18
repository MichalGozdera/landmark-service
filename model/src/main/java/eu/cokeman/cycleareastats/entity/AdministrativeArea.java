package eu.cokeman.cycleareastats.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import eu.cokeman.cycleareastats.valueObject.AreaName;
import eu.cokeman.cycleareastats.entity.AdministrativeLevel;
import eu.cokeman.cycleareastats.valueObject.LandmarkMetadata;

public class AdministrativeArea extends BaseEntity<AdministrativeAreaId>  {
    private AreaName name;
    private AdministrativeLevel level;
    private Serializable geometry;
    private AdministrativeAreaId parent;

    private AdministrativeArea(Builder builder) {
        super.setId(builder.id);
        super.setCreateTime(builder.createTime);
        super.setUpdateTime(builder.updateTime);
        this.name = builder.name;
        this.level = builder.level;
        this.geometry = builder.geometry;
        this.parent = builder.parent;
    }


    public Builder toBuilder() {
        return builder()
            .id(this.getId())
            .name(this.name)
            .level(this.level)
            .geometry(this.geometry)
            .parent(this.parent)
            .createTime(this.getCreateTime())
            .updateTime(this.getUpdateTime());
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private AdministrativeAreaId id;
        private AreaName name;
        private AdministrativeLevel level;
        private Serializable geometry;
        private AdministrativeAreaId parent;
        private Instant createTime;
        private Instant updateTime;

        public Builder id(AdministrativeAreaId id) { this.id = id; return this; }
        public Builder name(AreaName name) { this.name = name; return this; }
        public Builder level(AdministrativeLevel level) { this.level = level; return this; }
        public Builder geometry(Serializable geometry) { this.geometry = geometry; return this; }
        public Builder parent(AdministrativeAreaId parent) { this.parent = parent; return this; }
        public Builder createTime(Instant createTime) { this.createTime = createTime; return this; }
        public Builder updateTime(Instant updateTime) { this.updateTime = updateTime; return this; }
        public AdministrativeArea build() { return new AdministrativeArea(this); }
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


    public AdministrativeAreaId getParent() {
        return parent;
    }
}
