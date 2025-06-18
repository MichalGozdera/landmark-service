package eu.cokeman.cycleareastats.out.persistence.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "country", schema = "landmarks")
public class CountryEntity extends BaseJpaEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

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
}

