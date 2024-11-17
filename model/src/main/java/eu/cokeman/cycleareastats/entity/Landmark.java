package eu.cokeman.cycleareastats.entity;


import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import eu.cokeman.cycleareastats.valueObject.*;


public class Landmark extends AggregateRoot<LandmarkId> {

    private LandmarkName name;
    private LandmarkGeometryType geometryType;
    private LandmarkCategory category;
    private Instant loadTime;
    private Serializable geometry;
    private Country country;
    private LandmarkMetadata metadata;
    private LocalDate validto;
    private LandmarkId parent;

    private Landmark(Builder builder) {
        super.setId(builder.id);
        name = builder.name;
        geometryType = builder.geometryType;
        category = builder.category;
        loadTime = builder.loadTime;
        geometry = builder.geometry;
        country = builder.country;
        metadata = builder.metadata;
        parent = builder.parent;
    }

    public void initLandmark() {
        this.setId(new LandmarkId(UUID.randomUUID()));
        this.loadTime=Instant.now();
    }

    public LandmarkGeometryType getGeometryType() {
        return geometryType;
    }

    public LandmarkName getName() {
        return name;
    }

    public LandmarkCategory getCategory() {
        return category;
    }

    public Instant getLoadTime() {
        return loadTime;
    }

    public Serializable getGeometry() {
        return geometry;
    }

    public Country getCountry() {
        return country;
    }

    public LandmarkMetadata getMetadata() {
        return metadata;
    }

    public void  addDataFromImportedGeometry (LandmarkName name, Serializable geometry) {
        this.geometry = geometry;
        this.name = name;
    }

    public LandmarkId getParent() {
        return parent;
    }

    LocalDate getValidto() {
        return validto;
    }

    public void setParent(LandmarkId parent) {
        this.parent = parent;
    }

    public static final class Builder {

        private LandmarkId id;
        private LandmarkName name;
        private LandmarkGeometryType geometryType;
        private LandmarkCategory category;
        private Instant loadTime;
        private Serializable geometry;
        private Country country;
        private LandmarkMetadata metadata;
        private LocalDate validTo;
        private LandmarkId parent;

        public Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(LandmarkId val) {
            id = val;
            return this;
        }

        public Builder name(LandmarkName val) {
            name = val;
            return this;
        }

        public Builder geometryType(LandmarkGeometryType val) {
            geometryType = val;
            return this;
        }

        public Builder category(LandmarkCategory val) {
            category = val;
            return this;
        }

        public Builder loadTime(Instant val) {
            loadTime = val;
            return this;
        }

        public Builder geometry(Serializable val) {
            geometry = val;
            return this;
        }

        public Builder country(Country val) {
            country = val;
            return this;
        }

        public Builder metadata(LandmarkMetadata val) {
            metadata = val;
            return this;
        }

        public Builder validTo(LocalDate validTo) {
            validTo = validTo;
            return this;
        }

        public Builder parent(LandmarkId parent) {
            parent = parent;
            return this;
        }

        public Landmark build() {
            return new Landmark(this);
        }
    }
}
