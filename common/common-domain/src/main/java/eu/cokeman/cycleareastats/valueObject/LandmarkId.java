package eu.cokeman.cycleareastats.valueObject;

import java.util.Objects;
import java.util.UUID;

public record LandmarkId(UUID value) {
    public LandmarkId {
        Objects.requireNonNull(value, "'value' must not be null");
    }
}
