package eu.cokeman.cycleareastats.valueObject;

import java.util.Objects;

public record AdministrativeAreaId(Integer value) {
  public AdministrativeAreaId {
    Objects.requireNonNull(value, "'value' must not be null");
  }
}
