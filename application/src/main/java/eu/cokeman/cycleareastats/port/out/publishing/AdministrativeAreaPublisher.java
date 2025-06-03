package eu.cokeman.cycleareastats.port.out.publishing;

import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;

public interface AdministrativeAreaPublisher {

    void publish(AdministrativeAreaId areaId);
}
