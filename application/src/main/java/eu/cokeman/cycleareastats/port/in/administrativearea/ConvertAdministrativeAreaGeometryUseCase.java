package eu.cokeman.cycleareastats.port.in.administrativearea;

import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaGeometry;

import java.util.List;
import java.util.Set;


public interface ConvertAdministrativeAreaGeometryUseCase {

    Set<AdministrativeAreaGeometry> convertToLandmarksGeometries(Object geometry);

    List<String> getGeometriesSimplified(Object geometry);
}
