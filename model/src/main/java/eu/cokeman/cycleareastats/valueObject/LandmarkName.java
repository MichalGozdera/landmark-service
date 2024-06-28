package eu.cokeman.cycleareastats.valueObject;

import java.util.Objects;

public record LandmarkName(String name) {
    public LandmarkName {
        Objects.requireNonNull(name, "'name' must not be null");
        if (name.isEmpty()) {
            throw new IllegalArgumentException("'name' must not be empty");
        }
    }
}
