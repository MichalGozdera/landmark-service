package eu.cokeman.cycleareastats.valueObject;

import java.util.Objects;
import java.util.UUID;

public record AdministrativeAreaId(UUID value) {
    public AdministrativeAreaId {
        Objects.requireNonNull(value, "'value' must not be null");
    }
}
