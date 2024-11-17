package eu.cokeman.cycleareastats.port.in;

import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;


public interface ImportLandmarkUseCase {

    void importLandmark(Landmark landmark, Object geometry);

    void processChildren(LandmarkId landmarkId);
}
