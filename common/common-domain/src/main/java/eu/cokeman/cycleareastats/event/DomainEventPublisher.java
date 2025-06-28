package eu.cokeman.cycleareastats.event;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
