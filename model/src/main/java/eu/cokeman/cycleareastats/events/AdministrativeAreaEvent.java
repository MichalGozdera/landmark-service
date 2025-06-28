package eu.cokeman.cycleareastats.events;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.event.DomainEvent;

import java.time.Instant;



public class AdministrativeAreaEvent implements DomainEvent {

    private final AdministrativeArea area;
    private final Instant createdAt;
    private final String operationType;

    public  AdministrativeAreaEvent(AdministrativeArea area, String type) {
        this.area = area;
        this.operationType = type;
        this.createdAt = Instant.now();
    }

    public AdministrativeArea getArea() {
        return area;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    String getOperationType() {
        return operationType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private AdministrativeArea area;
        private Instant createdAt;
        private String operationType;

        public Builder area(AdministrativeArea area) {
            this.area = area;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder operationType(String operationType) {
            this.operationType = operationType;
            return this;
        }

        public AdministrativeAreaEvent build() {
            AdministrativeAreaEvent event = new AdministrativeAreaEvent(area, operationType);
            if (createdAt != null) {
                // ustawienie pola createdAt przez refleksję, bo jest final
                try {
                    java.lang.reflect.Field field = AdministrativeAreaEvent.class.getDeclaredField("createdAt");
                    field.setAccessible(true);
                    field.set(event, createdAt);
                } catch (Exception e) {
                    throw new RuntimeException("Nie można ustawić createdAt", e);
                }
            }
            return event;
        }
    }
}
