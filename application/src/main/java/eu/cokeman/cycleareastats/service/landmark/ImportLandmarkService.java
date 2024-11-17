package eu.cokeman.cycleareastats.service.landmark;

import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.port.in.ConvertLandmarkGeometryUseCase;
import eu.cokeman.cycleareastats.port.in.ImportLandmarkUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.LandmarkRepository;
import eu.cokeman.cycleareastats.port.out.publishing.LandmarkPublisher;
import eu.cokeman.cycleareastats.valueObject.LandmarkGeometry;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;
import eu.cokeman.cycleareastats.valueObject.LandmarkName;

import java.util.List;


public class ImportLandmarkService implements ImportLandmarkUseCase {

    private final LandmarkRepository landmarkRepository;
    private final LandmarkPublisher publisher;
    private final ConvertLandmarkGeometryUseCase converter;

    public ImportLandmarkService(LandmarkRepository landmarkRepository, LandmarkPublisher publisher, ConvertLandmarkGeometryUseCase converter) {
        this.landmarkRepository = landmarkRepository;
        this.publisher = publisher;
        this.converter = converter;
    }

    @Override
    public void processChildren(LandmarkId landmarkId) {
        Landmark landmark = landmarkRepository.findByLandmarkId(landmarkId);
        LandmarkId parentID = landmarkRepository.findParent(landmarkId);
        landmark.setParent(parentID);
        landmarkRepository.updateLandmark(landmark);

        List<Landmark> children = landmarkRepository.findChildren(landmarkId);
        if (children != null && !children.isEmpty()) {
            for (Landmark child : children) {
                child.setParent(landmarkId);
                landmarkRepository.updateLandmark(child);
            }
        }
    }

    @Override
    public void importLandmark(Landmark landmark, Object geometry) {
        var geometriesConverted = converter.convertToLandmarksGeometries(geometry);
        var ids = geometriesConverted.stream().map(landmarkGeometry -> importSingleLandmark(landmark, landmarkGeometry)).toList();
        ids.stream().forEach(id -> publisher.publish(id));
    }

    private LandmarkId importSingleLandmark(Landmark landmark, LandmarkGeometry geomertyData) {
        landmark.initLandmark();
        landmark.addDataFromImportedGeometry(new LandmarkName(geomertyData.name()), geomertyData.geometryData());
        return landmarkRepository.importLandmark(landmark);
    }
}
