package eu.cokeman.cycleareastats.port.in;

import eu.cokeman.cycleareastats.Landmark;
import eu.cokeman.cycleareastats.LandmarkID;

import java.util.List;

public interface NestLandmarkUseCase {

    Landmark nestLandmarks(LandmarkID parent, List<Landmark> children);
}
