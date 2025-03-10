package eu.cokeman.cycleareastats.port.in;

import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;
import java.util.List;

public interface NestLandmarkUseCase {

    Landmark nestLandmarks(LandmarkId parent, List<Landmark> children);
}
