package eu.cokeman.cycleareastats.port.out.publishing;

import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;

public interface LandmarkPublisher {

    void publish(LandmarkId landmark);
}
