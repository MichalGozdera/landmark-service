package eu.cokeman.cycleareastats.out.persistence.jpa.entity;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name = "administrative_area", schema = "landmarks")
public class AdministrativeAreaEntity extends AdministrativeAreaBaseEntity {
  private Geometry geometry;

  public Geometry getGeometry() {
    return geometry;
  }

  public void setGeometry(Geometry geometry) {
    this.geometry = geometry;
  }
}
