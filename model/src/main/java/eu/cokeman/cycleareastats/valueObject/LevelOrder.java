package eu.cokeman.cycleareastats.valueObject;

public record LevelOrder(Integer order) {
  public LevelOrder {
    if (order == null) {
      throw new IllegalArgumentException("order cannot be null");
    }
  }
}
