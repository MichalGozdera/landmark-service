package eu.cokeman.cycleareastats.valueObject;

import java.util.Objects;
import java.util.UUID;

public record LandmarkScoreId(UUID value) {
    public LandmarkScoreId {
        Objects.requireNonNull(value, "'value' must not be null");
    }
}
