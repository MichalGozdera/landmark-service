package eu.cokeman.cycleareastats.out.persistence.jpa.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;


@MappedSuperclass
public abstract class AdministrativeAreaBaseEntity extends BaseJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ColumnTransformer(write = "?::jsonb")
    private String metadata;
    private Integer parent;

    @JoinColumn(name = "level_id")
    @OneToOne
    private AdministrativeLevelEntity level;

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

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public AdministrativeLevelEntity getLevel() {
        return level;
    }

    public void setLevel(AdministrativeLevelEntity level) {
        this.level = level;
    }
}

