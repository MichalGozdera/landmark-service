package eu.cokeman.cycleareastats.converters.kml;

import com.axiomalaska.polylineencoder.PolylineEncoder;
import com.axiomalaska.polylineencoder.UnsupportedGeometryTypeException;
import eu.cokeman.cycleareastats.port.in.administrativearea.ConvertAdministrativeAreaGeometryUseCase;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaGeometry;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.kml.KMLReader;
import org.locationtech.jts.io.kml.KMLWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class KmlConverter implements ConvertAdministrativeAreaGeometryUseCase {


    public String convertToKml(List<AdministrativeAreaGeometry> geometries) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n");
        sb.append("<Document>\n");
        KMLWriter kmlWriter = new KMLWriter();
        kmlWriter.setMaximumCoordinatesPerLine(Integer.MAX_VALUE);
        for (AdministrativeAreaGeometry geometry : geometries) {
            if (geometry.geometryData() != null) {
                sb.append("<Placemark>\n");
                sb.append("<name>").append(geometry.name()).append("</name>\n");
                String kmlGeometry = kmlWriter.write((Geometry) geometry.geometryData());
                sb.append(kmlGeometry).append("\n");
                sb.append("</Placemark>\n");
            }
        }
        sb.append("</Document>\n</kml>");
        return sb.toString();
    }


    @Override
    public Set<AdministrativeAreaGeometry> convertToLandmarksGeometries(Object geometry) {
        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(((MultipartFile) geometry).getResource().getContentAsString(StandardCharsets.UTF_8))));
            doc.getDocumentElement().normalize();
            return processPlacemarks(doc.getElementsByTagName("Placemark"));
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private Set<AdministrativeAreaGeometry> processPlacemarks(NodeList placemarks) {
        Set<AdministrativeAreaGeometry> geometries = new HashSet<>();
        for (int i = 0; i < placemarks.getLength(); i++) {
            var children = placemarks.item(i).getChildNodes();
            String name = "";
            for (int j = 0; j < children.getLength(); j++) {

                if (children.item(j).getNodeName().equals("name")) {
                    name = children.item(j).getTextContent();
                }
                if (children.item(j).getNodeName().equals("MultiGeometry")) {
                    try {
                        KMLReader reader = new KMLReader();
                        geometries.add(new AdministrativeAreaGeometry(name, convert2D(reader.read(getString(children.item(j))))));
                    } catch (TransformerException e) {
                        throw new RuntimeException(e);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return geometries;
    }

    private Geometry convert2D(Geometry geometry) {

        var newGeom = tranferToMultipolygon(geometry);
        return newGeom;
    }

    private Geometry tranferToMultipolygon(Geometry geometry) {
        var numGeometries = geometry.getNumGeometries();
        Polygon[] result = new Polygon[numGeometries];
        for (int i = 0; i < numGeometries; i++) {
            Geometry gem = geometry.getGeometryN(i);
            Coordinate[] coords = gem.getCoordinates();
            Coordinate[] cords4d = new Coordinate[coords.length];
            for (int j = 0; j < coords.length; j++) {
                cords4d[j] = new Coordinate(coords[j].getX(), coords[j].getY());
            }

            // Check if the LineString is closed; if not, close it by adding the first coordinate at the end
            if (!coords[0].equals2D(cords4d[cords4d.length - 1])) {
                Coordinate[] closedCoords = new Coordinate[cords4d.length + 1];
                System.arraycopy(cords4d, 0, closedCoords, 0, cords4d.length);
                closedCoords[closedCoords.length - 1] = closedCoords[0];
                cords4d = closedCoords;
            }
            // Create a LinearRing from the coordinates
            LinearRing shell = geometry.getFactory().createLinearRing(cords4d);

            // Create the polygon with no holes (null for holes)
            result[i] = geometry.getFactory().createPolygon(shell, null);
        }
        MultiPolygon mp = new MultiPolygon(result, geometry.getFactory());
        return mp;
    }


    private static String getString(Node node) throws TransformerException {
        StringWriter sw = new StringWriter();
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(node), new StreamResult(sw));
        return sw.toString();
    }

    public List<String> getGeometriesSimplified(Object geometry) {
        Geometry geometryCasted = (Geometry) ((AdministrativeAreaGeometry) geometry).geometryData();
        switch (geometryCasted.getGeometryType()) {
            case "MultiPolygon" -> {
                return processMultiPolygon((MultiPolygon) geometryCasted);
            }
            default -> throw new IllegalArgumentException();
        }
    }

    private List<String> processMultiPolygon(MultiPolygon geometryCasted) {
        var parts = geometryCasted.getNumGeometries();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < parts; i++) {
            Polygon part = (Polygon) geometryCasted.getGeometryN(i);
            MultiLineString ms = polygonToMultiLineString(part, geometryCasted.getFactory());
            var newlines = processMultilineString(ms);
            result.addAll(newlines);
        }
        return result;
    }

    private MultiLineString polygonToMultiLineString(Polygon polygon, GeometryFactory geometryFactory) {
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
            var encoded = PolylineEncoder.encode(line).getPoints();
            return encoded.replace("\\", "\\\\");
        } catch (UnsupportedGeometryTypeException e) {
            throw new RuntimeException(e);
        }
    }


}
