package eu.cokeman.cycleareastats.events;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.event.DomainEvent;
import eu.cokeman.cycleareastats.valueObject.EntityEventType;

import java.time.Instant;



public class AdministrativeAreaEvent implements DomainEvent {

    private final AdministrativeArea area;
    private final Instant createdAt;
    private final EntityEventType operationType;

    public  AdministrativeAreaEvent(AdministrativeArea area, EntityEventType type) {
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

    public EntityEventType getOperationType() {
        return operationType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private AdministrativeArea area;
        private EntityEventType operationType;

        public Builder area(AdministrativeArea area) {
            this.area = area;
            return this;
        }

        public Builder operationType(EntityEventType operationType) {
            this.operationType = operationType;
            return this;
        }

        public AdministrativeAreaEvent build() {
            AdministrativeAreaEvent event = new AdministrativeAreaEvent(area, operationType);
            return event;
        }
    }
}
