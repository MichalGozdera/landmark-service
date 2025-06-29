package eu.cokeman.cycleareastats.port.in.administrativearea;

import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaSimplifiedGeometry;

import java.io.Serializable;

public interface PolylineEncoder {

    AdministrativeAreaSimplifiedGeometry getGeometriesSimplified(Serializable geometry);

}
