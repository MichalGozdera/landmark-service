package eu.cokeman.cycleareastats.out.persistence.jpa.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;
import org.locationtech.jts.geom.Geometry;

import java.util.UUID;

@Entity
@Table(name = "geometry_simplified", schema = "landmarks")
public class GeometrySimplifiedEntity extends BaseJpaEntity{


    @Id
    @SequenceGenerator(name = "geom_simpl_seq", sequenceName = "geometry_simplified_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "geom_simpl_seq")
    private Integer id;
    private String line;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private AdministrativeAreaEntity area_id;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public AdministrativeAreaEntity getArea_id() {
        return area_id;
    }

    public void setArea_id(AdministrativeAreaEntity area_id) {
        this.area_id = area_id;
    }

    public Integer getId() {
        return id;
    }

    public  void setId(Integer id) {
        this.id = id;
    }
}
