package eu.cokeman.cycleareastats.port.in.administrativearea;

import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaGeometry;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaSimplifiedGeometry;

import java.io.Serializable;
import java.util.List;
import java.util.Set;


public interface AdministrativeAreaConverter {

    Set<AdministrativeAreaGeometry> convertToLandmarksGeometries(Object geometry);


    String convertToKml(List<AdministrativeAreaGeometry> geometries);
}
