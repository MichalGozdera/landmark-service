package eu.cokeman.cycleareastats.converters.kml;

import com.axiomalaska.polylineencoder.UnsupportedGeometryTypeException;
import eu.cokeman.cycleareastats.port.in.administrativearea.PolylineEncoder;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaSimplifiedGeometry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.locationtech.jts.geom.*;

public class PolylineEncoderImpl implements PolylineEncoder {

  @Override
  public AdministrativeAreaSimplifiedGeometry getGeometriesSimplified(Serializable geometry) {
    Geometry geometryCasted = (Geometry) geometry;
    switch (geometryCasted.getGeometryType()) {
      case "MultiPolygon" -> {
        return processMultiPolygon((MultiPolygon) geometryCasted);
      }
      default -> throw new IllegalArgumentException();
    }
  }

  private AdministrativeAreaSimplifiedGeometry processMultiPolygon(MultiPolygon geometryCasted) {
    var parts = geometryCasted.getNumGeometries();
    List<String> result = new ArrayList<>();
    for (int i = 0; i < parts; i++) {
      Polygon part = (Polygon) geometryCasted.getGeometryN(i);
      MultiLineString ms = polygonToMultiLineString(part, geometryCasted.getFactory());
      var newlines = processMultilineString(ms);
      result.addAll(newlines);
    }
    return new AdministrativeAreaSimplifiedGeometry(result);
  }

  private MultiLineString polygonToMultiLineString(
      Polygon polygon, GeometryFactory geometryFactory) {
    int totalRings = 1 + polygon.getNumInteriorRing();
    LineString[] lineStrings = new LineString[totalRings];

    // Exterior ring
    lineStrings[0] = polygon.getExteriorRing();

    // Interior rings (holes)
    for (int i = 0; i < polygon.getNumInteriorRing(); i++) {
      lineStrings[i + 1] = polygon.getInteriorRingN(i);
    }

    return geometryFactory.createMultiLineString(lineStrings);
  }

  private List<String> processMultilineString(MultiLineString geometryCasted) {
    var parts = geometryCasted.getNumGeometries();
    List<String> result = new ArrayList<>();
    for (int i = 0; i < parts; i++) {
      result.add(parseLineString((LinearRing) geometryCasted.getGeometryN(i)));
    }
    return result;
  }

  private String parseLineString(LinearRing ring) {
    try {
      GeometryFactory factory = ring.getFactory();
      var line = factory.createLineString(ring.getCoordinateSequence());
      var encoded = com.axiomalaska.polylineencoder.PolylineEncoder.encode(line).getPoints();
      return encoded.replace("\\", "\\\\");
    } catch (UnsupportedGeometryTypeException e) {
      throw new RuntimeException(e);
    }
  }
}
