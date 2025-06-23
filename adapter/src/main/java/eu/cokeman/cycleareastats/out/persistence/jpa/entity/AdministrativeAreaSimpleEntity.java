package eu.cokeman.cycleareastats.out.persistence.jpa.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;

@Entity
@Table(name = "administrative_area", schema = "landmarks")
public class AdministrativeAreaSimpleEntity extends AdministrativeAreaBaseEntity {

}
