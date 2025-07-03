package eu.cokeman.cycleareastats.valueObject;

public record LevelName(String name) {
  public LevelName {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
  }
}
