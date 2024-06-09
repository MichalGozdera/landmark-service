package eu.cokeman.cycleareastats;


import java.time.OffsetDateTime;
import java.util.List;
import eu.cokeman.cycleareastats.valueObject.LandmarkGeometryType;
import eu.cokeman.cycleareastats.valueObject.LandmarkID;
import eu.cokeman.cycleareastats.valueObject.Country;



public class Landmark {

    private LandmarkGeometryType geometryType;
    private String name;
    private OffsetDateTime loadTime;
    private LandmarkID landmarkID;
    private String geometry;
    private Country country;
    private List<Landmark> landmarks;

    private Landmark(Builder builder) {
        geometryType = builder.geometryType;
        name = builder.name;
        loadTime = builder.loadTime;
        landmarkID = builder.landmarkID;
        geometry = builder.geometry;
        country = builder.country;
        landmarks = builder.landmarks;
    }

    public static final class Builder {
        private LandmarkGeometryType geometryType;
        private String name;
        private OffsetDateTime loadTime;
        private LandmarkID landmarkID;
        private String geometry;
        private Country country;
        private List<Landmark> landmarks;

        public Builder() {
        }

        public Builder geometryType(LandmarkGeometryType val) {
            geometryType = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder loadTime(OffsetDateTime val) {
            loadTime = val;
            return this;
        }

        public Builder landmarkID(LandmarkID val) {
            landmarkID = val;
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

        public Landmark build() {
            return new Landmark(this);
        }
    }
}
