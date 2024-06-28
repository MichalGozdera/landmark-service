package eu.cokeman.cycleareastats.out.persistence.jpa.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "landmark", schema="geo")
public class LandmarkEntity {


    @Id
    @Column(name = "landmarkid")
    private UUID id;
    private String name;
    private String geometrytype;
    private String category;
    private Instant loadtime;
    private String country;

    public UUID getid() {
        return id;
    }

    public void setid(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGeometrytype() {
        return geometrytype;
    }

    public void setGeometrytype(String geometrytype) {
        this.geometrytype = geometrytype;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Instant getLoadTime() {
        return loadtime;
    }

    public void setLoadTime(Instant loadTime) {
        this.loadtime = loadTime;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
