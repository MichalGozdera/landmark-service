package eu.cokeman.cycleareastats.valueObject;

import java.util.Objects;

public record CountryId(Integer value) {
    public CountryId {
        Objects.requireNonNull(value, "'value' must not be null");
    }
}

