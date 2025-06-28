package eu.cokeman.cycleareastats.valueObject;

import java.io.Serializable;
import java.util.List;

public record AdministrativeAreaSimplifiedGeometry(List<String> encodedLines) implements Serializable  {
}
