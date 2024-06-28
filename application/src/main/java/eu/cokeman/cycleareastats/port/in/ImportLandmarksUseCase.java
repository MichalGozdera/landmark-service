package eu.cokeman.cycleareastats.port.in;

import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.valueObject.Country;
import eu.cokeman.cycleareastats.valueObject.LandmarkGeometryType;
import eu.cokeman.cycleareastats.valueObject.LandmarkName;

public interface ImportLandmarksUseCase {

    void importLandmarks(String data[], Country country, LandmarkName name, LandmarkGeometryType type);
}
