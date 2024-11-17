package eu.cokeman.cycleareastats.port.in;

import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.valueObject.LandmarkGeometry;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;

import java.util.Set;


public interface ConvertLandmarkGeometryUseCase {

    Set<LandmarkGeometry> convertToLandmarksGeometries(Object geometry);
}
