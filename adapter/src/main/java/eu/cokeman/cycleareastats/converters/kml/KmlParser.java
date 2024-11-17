package eu.cokeman.cycleareastats.converters.kml;

import eu.cokeman.cycleareastats.port.in.ConvertLandmarkGeometryUseCase;
import eu.cokeman.cycleareastats.valueObject.LandmarkGeometry;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.CoordinateSequenceFactory;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.kml.KMLReader;
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
import java.util.*;


@Component
public class KmlParser implements ConvertLandmarkGeometryUseCase {


    @Override
    public Set<LandmarkGeometry> convertToLandmarksGeometries(Object geometry) {
        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(((MultipartFile)geometry).getResource().getContentAsString(StandardCharsets.UTF_8))));
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

    private Set<LandmarkGeometry> processPlacemarks(NodeList placemarks) {
        Set<LandmarkGeometry> geometries = new HashSet<>();
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
                        geometries.add(new LandmarkGeometry(name,convert2D(reader.read(getString(children.item(j))))));
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

    private Geometry convert2D(Geometry geometry){

        for(Coordinate c : geometry.getCoordinates()){
            c.setCoordinate(new Coordinate(c.x, c.y));
        }
        return geometry;
    }


    private static String getString(Node node) throws TransformerException {
        StringWriter sw = new StringWriter();
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(node), new StreamResult(sw));
        return sw.toString();
    }


}
