package eu.cokeman.cycleareastats.out.persistence.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "administrative_level", schema = "landmarks")
public class AdministrativeLevelEntity extends BaseJpaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "country", referencedColumnName = "id")
  private CountryEntity country;

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

  public CountryEntity getCountry() {
    return country;
  }

  public void setCountry(CountryEntity country) {
    this.country = country;
  }

  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }
}
