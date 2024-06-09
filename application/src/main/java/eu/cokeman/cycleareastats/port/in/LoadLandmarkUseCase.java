package eu.cokeman.cycleareastats.port.in;

import eu.cokeman.cycleareastats.Landmark;
import eu.cokeman.cycleareastats.LandmarkGeometryType;

public interface LoadLandmarkUseCase {

    Landmark loadLandmark(String data, String name, LandmarkGeometryType type);
}
