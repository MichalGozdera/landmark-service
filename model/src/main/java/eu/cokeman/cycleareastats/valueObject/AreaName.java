package eu.cokeman.cycleareastats.valueObject;


public record AreaName(String name) {
    public AreaName {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
    }
}
