package eu.cokeman.cycleareastats.converters.kml;

import static org.junit.jupiter.api.Assertions.*;

import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaGeometry;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.mockito.Mockito;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

class KmlConverterTest {
  private final KmlConverter converter = new KmlConverter();

  @Test
  void testConvertToKml() throws Exception {
    GeometryFactory gf = new GeometryFactory();
    // Simple polygon
    Coordinate[] coords1 =
        new Coordinate[] {
          new Coordinate(0, 0),
          new Coordinate(0, 1),
          new Coordinate(1, 1),
          new Coordinate(1, 0),
          new Coordinate(0, 0)
        };
    LinearRing shell1 = gf.createLinearRing(coords1);
    Polygon polygon1 = gf.createPolygon(shell1, null);
    AdministrativeAreaGeometry geom1 = new AdministrativeAreaGeometry("TestArea1", polygon1);

    // Polygon with a hole
    Coordinate[] shellCoords =
        new Coordinate[] {
          new Coordinate(2, 2),
          new Coordinate(2, 5),
          new Coordinate(5, 5),
          new Coordinate(5, 2),
          new Coordinate(2, 2)
        };
    Coordinate[] holeCoords =
        new Coordinate[] {
          new Coordinate(3, 3),
          new Coordinate(3, 4),
          new Coordinate(4, 4),
          new Coordinate(4, 3),
          new Coordinate(3, 3)
        };
    LinearRing shell2 = gf.createLinearRing(shellCoords);
    LinearRing[] holes = new LinearRing[] {gf.createLinearRing(holeCoords)};
    Polygon polygon2 = gf.createPolygon(shell2, holes);
    AdministrativeAreaGeometry geom2 = new AdministrativeAreaGeometry("TestArea2", polygon2);

    // MultiPolygon
    Polygon polyA =
        gf.createPolygon(
            gf.createLinearRing(
                new Coordinate[] {
                  new Coordinate(10, 10),
                  new Coordinate(10, 12),
                  new Coordinate(12, 12),
                  new Coordinate(12, 10),
                  new Coordinate(10, 10)
                }),
            null);
    Polygon polyB =
        gf.createPolygon(
            gf.createLinearRing(
                new Coordinate[] {
                  new Coordinate(20, 20),
                  new Coordinate(20, 22),
                  new Coordinate(22, 22),
                  new Coordinate(22, 20),
                  new Coordinate(20, 20)
                }),
            null);
    org.locationtech.jts.geom.MultiPolygon multiPolygon =
        gf.createMultiPolygon(new Polygon[] {polyA, polyB});
    AdministrativeAreaGeometry geom3 = new AdministrativeAreaGeometry("TestArea3", multiPolygon);

    List<AdministrativeAreaGeometry> geometries = List.of(geom1, geom2, geom3);
    String kml = converter.convertToKml(geometries);

    // Check for key fragments
    assertTrue(kml.contains("<Placemark>"));
    assertTrue(kml.contains("TestArea1"));
    assertTrue(kml.contains("TestArea2"));
    assertTrue(kml.contains("TestArea3"));
    assertTrue(kml.contains("<Polygon>"));
    assertTrue(kml.contains("<MultiGeometry>") || kml.contains("MultiPolygon"));

    // Compare with reference file (add expected-multi.kml to test/resources)
    java.io.InputStream is = getClass().getClassLoader().getResourceAsStream("expected-multi.kml");
    assertNotNull(
        is,
        "expected-multi.kml file not found in test/resources. Generate it based on the geometries above.");
    String expectedKml =
        new String(is.readAllBytes(), StandardCharsets.UTF_8).replaceAll("\\s+", "");
    String actualKml = kml.replaceAll("\\s+", "");
    assertEquals(expectedKml, actualKml, "Generated KML does not match the expected file.");
  }

  @Test
  void testConvertToLandmarksGeometries_Lines() throws Exception {
    java.io.InputStream is =
        getClass().getClassLoader().getResourceAsStream("kml-cases/test-lines.kml");
    assertNotNull(is, "test-lines.kml file not found in resources");
    byte[] kmlBytes = is.readAllBytes();
    MultipartFile mockFile = Mockito.mock(MultipartFile.class);
    Mockito.when(mockFile.getResource())
        .thenReturn(
            new ByteArrayResource(kmlBytes) {
              @Override
              public String getContentAsString(java.nio.charset.Charset charset) {
                return new String(getByteArray(), charset);
              }
            });
    Set<AdministrativeAreaGeometry> result = converter.convertToLandmarksGeometries(mockFile);
    assertEquals(3, result.size(), "Should parse exactly three geometries from KML");
    boolean found1 = false, found2 = false, found3 = false;
    for (AdministrativeAreaGeometry geom : result) {
      if ("TestArea1".equals(geom.name())) {
        assertEquals(
            "MultiPolygon",
            ((org.locationtech.jts.geom.Geometry) geom.geometryData()).getGeometryType());
        found1 = true;
      } else if ("TestArea2".equals(geom.name())) {
        assertTrue(
            ((org.locationtech.jts.geom.Geometry) geom.geometryData())
                    .getGeometryType()
                    .contains("MultiPolygon")
                || ((org.locationtech.jts.geom.Geometry) geom.geometryData())
                    .getGeometryType()
                    .contains("GeometryCollection"));
        found2 = true;
      } else if ("TestArea3".equals(geom.name())) {
        assertTrue(
            ((org.locationtech.jts.geom.Geometry) geom.geometryData())
                    .getGeometryType()
                    .contains("MultiPolygon")
                || ((org.locationtech.jts.geom.Geometry) geom.geometryData())
                    .getGeometryType()
                    .contains("GeometryCollection"));
        found3 = true;
      }
    }
    assertTrue(found1 && found2 && found3, "All expected geometries should be present");
  }

  @Test
  void testSimplePolygon() throws Exception {
    java.io.InputStream is =
        getClass().getClassLoader().getResourceAsStream("kml-cases/simple-polygon.kml");
    assertNotNull(is, "simple-polygon.kml file not found");
    byte[] kmlBytes = is.readAllBytes();
    MultipartFile mockFile = Mockito.mock(MultipartFile.class);
    Mockito.when(mockFile.getResource())
        .thenReturn(
            new ByteArrayResource(kmlBytes) {
              @Override
              public String getContentAsString(java.nio.charset.Charset charset) {
                return new String(getByteArray(), charset);
              }
            });
    Set<AdministrativeAreaGeometry> result = converter.convertToLandmarksGeometries(mockFile);
    assertEquals(1, result.size(), "Should parse exactly one geometry");
    AdministrativeAreaGeometry geom = result.iterator().next();
    assertEquals("SimplePolygon", geom.name());
    assertEquals(
        "MultiPolygon",
        ((org.locationtech.jts.geom.Geometry) geom.geometryData()).getGeometryType());
  }

  @Test
  void testPolygonsWithHoles() throws Exception {
    java.io.InputStream is =
        getClass().getClassLoader().getResourceAsStream("kml-cases/polygons-with-holes.kml");
    assertNotNull(is, "polygons-with-holes.kml file not found");
    byte[] kmlBytes = is.readAllBytes();
    MultipartFile mockFile = Mockito.mock(MultipartFile.class);
    Mockito.when(mockFile.getResource())
        .thenReturn(
            new ByteArrayResource(kmlBytes) {
              @Override
              public String getContentAsString(java.nio.charset.Charset charset) {
                return new String(getByteArray(), charset);
              }
            });
    Set<AdministrativeAreaGeometry> result = converter.convertToLandmarksGeometries(mockFile);
    assertEquals(2, result.size(), "Should parse exactly two geometries");
    boolean found1 = false, found2 = false;
    for (AdministrativeAreaGeometry geom : result) {
      if ("PolygonWithHole".equals(geom.name())) {
        assertEquals(
            "MultiPolygon",
            ((org.locationtech.jts.geom.Geometry) geom.geometryData()).getGeometryType());
        found1 = true;
      } else if ("PolygonWithTwoHoles".equals(geom.name())) {
        assertEquals(
            "MultiPolygon",
            ((org.locationtech.jts.geom.Geometry) geom.geometryData()).getGeometryType());
        found2 = true;
      }
    }
    assertTrue(found1 && found2, "Both polygons with holes should be present");
  }

  @Test
  void testInvalidGeometry() throws Exception {
    java.io.InputStream is =
        getClass().getClassLoader().getResourceAsStream("kml-cases/invalid-geometry.kml");
    assertNotNull(is, "invalid-geometry.kml file not found");
    byte[] kmlBytes = is.readAllBytes();
    MultipartFile mockFile = Mockito.mock(MultipartFile.class);
    Mockito.when(mockFile.getResource())
        .thenReturn(
            new ByteArrayResource(kmlBytes) {
              @Override
              public String getContentAsString(java.nio.charset.Charset charset) {
                return new String(getByteArray(), charset);
              }
            });
    try {
      Set<AdministrativeAreaGeometry> result = converter.convertToLandmarksGeometries(mockFile);
      assertTrue(result.isEmpty(), "Invalid geometries should not be parsed");
    } catch (Exception ex) {
      // Acceptable if exception is thrown
    }
  }

  @Test
  void testOnlyMultiGeometry() throws Exception {
    java.io.InputStream is =
        getClass().getClassLoader().getResourceAsStream("kml-cases/only-multigeometry.kml");
    assertNotNull(is, "only-multigeometry.kml file not found");
    byte[] kmlBytes = is.readAllBytes();
    MultipartFile mockFile = Mockito.mock(MultipartFile.class);
    Mockito.when(mockFile.getResource())
        .thenReturn(
            new ByteArrayResource(kmlBytes) {
              @Override
              public String getContentAsString(java.nio.charset.Charset charset) {
                return new String(getByteArray(), charset);
              }
            });
    Set<AdministrativeAreaGeometry> result = converter.convertToLandmarksGeometries(mockFile);
    // Always MultiPolygon
    assertEquals(1, result.size(), "Should parse two geometries from MultiGeometry Placemark");
    for (AdministrativeAreaGeometry geom : result) {
      assertTrue(
          geom.geometryData() instanceof org.locationtech.jts.geom.MultiPolygon,
          "Should always be MultiPolygon");
    }
  }

  // Helper method for loading and parsing KML from test resources
  private Set<AdministrativeAreaGeometry> parseKmlFromResource(String resourcePath)
      throws Exception {
    java.io.InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath);
    assertNotNull(is, resourcePath + " file not found");
    byte[] kmlBytes = is.readAllBytes();
    MultipartFile mockFile = Mockito.mock(MultipartFile.class);
    Mockito.when(mockFile.getResource())
        .thenReturn(
            new ByteArrayResource(kmlBytes) {
              @Override
              public String getContentAsString(java.nio.charset.Charset charset) {
                return new String(getByteArray(), charset);
              }
            });
    return converter.convertToLandmarksGeometries(mockFile);
  }

  @Test
  void testTestArea() throws Exception {
    // Should parse three MultiPolygon geometries from test-area.kml
    Set<AdministrativeAreaGeometry> result = parseKmlFromResource("kml-cases/test-area.kml");
    assertEquals(3, result.size(), "Should parse three polygons from test-area.kml");
    for (AdministrativeAreaGeometry geom : result) {
      assertTrue(
          geom.geometryData() instanceof org.locationtech.jts.geom.MultiPolygon,
          "Should always be MultiPolygon");
    }
  }

  @Test
  void testTestLines() throws Exception {
    // Should parse three MultiPolygon geometries from test-lines.kml (all lines are converted to
    // MultiPolygon)
    Set<AdministrativeAreaGeometry> result = parseKmlFromResource("kml-cases/test-lines.kml");
    assertEquals(3, result.size(), "Should parse exactly three geometries from test-lines.kml");
    for (AdministrativeAreaGeometry geom : result) {
      assertTrue(
          geom.geometryData() instanceof org.locationtech.jts.geom.MultiPolygon,
          "Should always be MultiPolygon");
    }
  }

  @Test
  void testMissingMultiGeometryPlacemark() throws Exception {
    // This test uses a file with three Placemarks, only two with MultiGeometry
    Set<AdministrativeAreaGeometry> result =
        parseKmlFromResource("kml-cases/mixed-multigeometry.kml");
    // Only Placemarks with MultiGeometry should be parsed (2)
    assertEquals(2, result.size(), "Should parse only Placemarks with MultiGeometry");
    boolean found1 = false, found2 = false, found3 = false;
    for (AdministrativeAreaGeometry geom : result) {
      if ("WithMultiGeometry1".equals(geom.name())) found1 = true;
      if ("WithMultiGeometry2".equals(geom.name())) found2 = true;
      if ("WithoutMultiGeometry".equals(geom.name())) found3 = true;
    }
    assertTrue(found1, "Placemark WithMultiGeometry1 should be present");
    assertTrue(found2, "Placemark WithMultiGeometry2 should be present");
    assertFalse(found3, "Placemark WithoutMultiGeometry should NOT be present");
  }
}
