package eu.cokeman.cycleareastats.port.in;

import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;

public interface UpdateLandmarkUseCase {
    Landmark updateLandmark(Landmark landmark);
}
