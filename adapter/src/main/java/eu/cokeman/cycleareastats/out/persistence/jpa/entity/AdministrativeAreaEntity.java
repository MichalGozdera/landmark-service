package eu.cokeman.cycleareastats.out.persistence.jpa.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.locationtech.jts.geom.Geometry;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "administrative_area", schema = "landmarks")
public class AdministrativeAreaEntity extends BaseJpaEntity{


    @Id
    @Column(name = "uuid")
    private UUID uuid;
    private String name;

    @ColumnTransformer(write = "?::jsonb")
    private String metadata;
    private Geometry geometry;
    private UUID parent;
    @OneToMany(mappedBy = "area_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GeometrySimplifiedEntity> geometriesSimplified = new ArrayList<>();

    @JoinColumn(name = "level_id")
    @OneToOne
    private AdministrativeLevelEntity level;

    public AdministrativeLevelEntity getLevel() {
        return level;
    }

    public void setLevel(AdministrativeLevelEntity level) {
        this.level = level;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID id) {
        this.uuid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geom) {
        this.geometry = geom;
    }

    public UUID getParent() {
        return parent;
    }

    public void setParent(UUID parent) {
        this.parent = parent;
    }

    public void addGeometrySimplified(GeometrySimplifiedEntity geometrySimplified) {
        geometriesSimplified.add(geometrySimplified);
        geometrySimplified.setArea_id(this);
    }

    public void removeGeometrySimplified(GeometrySimplifiedEntity geometrySimplified) {
        geometriesSimplified.remove(geometrySimplified);
    }

    public List<GeometrySimplifiedEntity> getGeometriesSimplified() {
        return geometriesSimplified;
    }

    public void setGeometriesSimplified(List<GeometrySimplifiedEntity> geometriesSimplified) {
        this.geometriesSimplified = geometriesSimplified;
    }
}
