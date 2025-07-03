package eu.cokeman.cycleareastats.converters.kml;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.*;

class PolylineEncoderImplTest {
  @Test
  void processMultiPolygon_shouldReturnEncodedLines() {
    GeometryFactory factory = new GeometryFactory();
    Coordinate[] coords1 =
        new Coordinate[] {
          new Coordinate(0, 0),
          new Coordinate(0, 1),
          new Coordinate(1, 1),
          new Coordinate(1, 0),
          new Coordinate(0, 0)
        };
    Coordinate[] coords2 =
        new Coordinate[] {
          new Coordinate(2, 2),
          new Coordinate(2, 3),
          new Coordinate(3, 3),
          new Coordinate(3, 2),
          new Coordinate(2, 2)
        };
    Polygon poly1 = factory.createPolygon(coords1);
    Polygon poly2 = factory.createPolygon(coords2);
    MultiPolygon multiPolygon = factory.createMultiPolygon(new Polygon[] {poly1, poly2});

    PolylineEncoderImpl encoder = new PolylineEncoderImpl();
    var result = encoder.getGeometriesSimplified(multiPolygon);
    assertNotNull(result);
    assertThat(result.encodedLines()).hasSize(2);
  }

  @Test
  void processMultiPolygon_shouldThrowException_whenNotMultiPolygon() {
    GeometryFactory factory = new GeometryFactory();
    Coordinate[] coords =
        new Coordinate[] {
          new Coordinate(0, 0),
          new Coordinate(0, 1),
          new Coordinate(1, 1),
          new Coordinate(1, 0),
          new Coordinate(0, 0)
        };
    Polygon polygon = factory.createPolygon(coords);
    PolylineEncoderImpl encoder = new PolylineEncoderImpl();
    assertThrows(IllegalArgumentException.class, () -> encoder.getGeometriesSimplified(polygon));
  }
}
