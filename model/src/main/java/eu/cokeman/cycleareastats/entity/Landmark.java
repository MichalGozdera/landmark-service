package eu.cokeman.cycleareastats.entity;


import java.time.Instant;
import java.util.List;
import java.util.UUID;

import eu.cokeman.cycleareastats.valueObject.*;


public class Landmark extends AggregateRoot<LandmarkId> {

    private LandmarkName name;
    private LandmarkGeometryType geometryType;
    private LandmarkCategory category;
    private Instant loadTime;
    private String geometry;
    private Country country;
    private LandmarkMetadata metadata;
    private List<Landmark> landmarks;

    public void initLandmark() {
        this.setId(new LandmarkId(UUID.randomUUID()));
    }

    private Landmark(Builder builder) {
        super.setId(builder.id);
        geometryType = builder.geometryType;
        name = builder.name;
        category = builder.category;
        loadTime = builder.loadTime;
        geometry = builder.geometry;
        country = builder.country;
        metadata = builder.metadata;
        landmarks = builder.landmarks;
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

    public String getGeometry() {
        return geometry;
    }

    public Country getCountry() {
        return country;
    }

    public LandmarkMetadata getMetadata() {
        return metadata;
    }

    List<Landmark> getLandmarks() {
        return landmarks;
    }


    public static final class Builder {
        private LandmarkId id;
        private LandmarkGeometryType geometryType;
        private LandmarkName name;
        private LandmarkCategory category;
        private Instant loadTime;
        private String geometry;
        private Country country;
        private LandmarkMetadata metadata;
        private List<Landmark> landmarks;

        public Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(LandmarkId val) {
            id = val;
            return this;
        }

        public Builder geometryType(LandmarkGeometryType val) {
            geometryType = val;
            return this;
        }

        public Builder name(LandmarkName val) {
            name = val;
            return this;
        }

        public Builder category(LandmarkCategory val) {
            category = val;
            return this;
        }

        public Builder loadTime(Instant val) {
            loadTime = Instant.now();
            return this;
        }

        public Builder geometry(String val) {
            geometry = val;
            return this;
        }

        public Builder country(Country val) {
            country = val;
            return this;
        }

        public Builder landmarks(List<Landmark> val) {
            landmarks = val;
            return this;
        }

        public Builder metadata(LandmarkMetadata val) {
            metadata = val;
            return this;
        }

        public Landmark build() {
            return new Landmark(this);
        }
    }
}
