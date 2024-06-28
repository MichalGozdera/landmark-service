package eu.cokeman.cycleareastats.port.out.persistence;


import eu.cokeman.cycleareastats.valueObject.Country;
import eu.cokeman.cycleareastats.valueObject.LandmarkGeometryType;
import eu.cokeman.cycleareastats.valueObject.LandmarkName;

import java.util.List;


public interface LandmarksRepository {
    void importLandmarks(String data[], Country country, LandmarkName name, LandmarkGeometryType type);


}
