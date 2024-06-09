package eu.cokeman.cycleareastats.port.in;

import eu.cokeman.cycleareastats.Landmark;
import eu.cokeman.cycleareastats.LandmarkID;

public interface FetchLandmarkUseCase {

    Landmark getLandmark(LandmarkID landmarkID);
}
