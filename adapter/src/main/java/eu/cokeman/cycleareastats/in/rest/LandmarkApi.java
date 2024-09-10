package eu.cokeman.cycleareastats.in.rest;

import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.mapper.LandmarkExternalMapper;
import eu.cokeman.cycleareastats.openapi.model.LandmarkDto;
import eu.cokeman.cycleareastats.port.in.FetchLandmarkUseCase;
import eu.cokeman.cycleareastats.port.in.ImportLandmarkUseCase;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.UUID;


@RestController
public class LandmarkApi implements eu.cokeman.cycleareastats.openapi.api.LandmarkApi {

    private final ImportLandmarkUseCase importLandmarkUseCase;
    private final FetchLandmarkUseCase fetchLandmarkUseCase;
    LandmarkExternalMapper mapper = LandmarkExternalMapper.INSTANCE;

    public LandmarkApi(ImportLandmarkUseCase importLandmarkUseCase, FetchLandmarkUseCase fetchLandmarkUseCase) {
        this.importLandmarkUseCase = importLandmarkUseCase;
        this.fetchLandmarkUseCase = fetchLandmarkUseCase;
    }

    @Override
    public ResponseEntity<Void> importLandmark(LandmarkDto landmarkDto, MultipartFile geometry) {
        Landmark landMark = mapper.mapToInternal(landmarkDto).build();
        var result = importLandmarkUseCase.importLandmark(landMark);
        return ResponseEntity.created(URI.create(result.value().toString())).build();
    }

    @Override
    public ResponseEntity<LandmarkDto> loadLandmark(String landmarkIdExternal) {
        LandmarkId landmarkInternalId = mapper.mapToLandmarkid(UUID.fromString(landmarkIdExternal));
        Landmark landmark= fetchLandmarkUseCase.findLandmark(landmarkInternalId);
        LandmarkDto landmarkResponse = mapper.mapToExternal(landmark);
        return ResponseEntity.ok(landmarkResponse);
    }

}
