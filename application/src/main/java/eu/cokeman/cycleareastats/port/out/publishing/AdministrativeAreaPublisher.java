package eu.cokeman.cycleareastats.port.out.publishing;

import eu.cokeman.cycleareastats.events.AdministrativeAreaEvent;

public interface AdministrativeAreaPublisher {

    void publish(AdministrativeAreaEvent event);
}
