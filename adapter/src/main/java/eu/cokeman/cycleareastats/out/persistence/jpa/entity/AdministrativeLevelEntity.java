package eu.cokeman.cycleareastats.out.persistence.jpa.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Generated;
import org.locationtech.jts.geom.Geometry;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "administrative_level", schema = "landmarks")
public class AdministrativeLevelEntity extends BaseJpaEntity {


    @Id
    @SequenceGenerator(name = "admin_level_seq", sequenceName = "administrative_level_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_level_seq")
    private Integer id;
    private String name;
    private String country;
    @Column(name = "level_order")
    private Integer order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}

