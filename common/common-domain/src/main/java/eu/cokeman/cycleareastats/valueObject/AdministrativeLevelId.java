package eu.cokeman.cycleareastats.valueObject;

import java.util.Objects;

public record AdministrativeLevelId(Integer value) {
    public AdministrativeLevelId {
        Objects.requireNonNull(value, "'value' must not be null");
    }
}
