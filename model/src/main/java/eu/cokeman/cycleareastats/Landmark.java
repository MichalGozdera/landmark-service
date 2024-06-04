package eu.cokeman.cycleareastats;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
public class Landmark {

    private LandmarkType type;
    private LandmarkID landmarkID;
    private String data;
    private List<Landmark> landmarks;

}
