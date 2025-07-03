package eu.cokeman.cycleareastats.out.persistence.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "administrative_area", schema = "landmarks")
public class AdministrativeAreaSimpleEntity extends AdministrativeAreaBaseEntity {}
