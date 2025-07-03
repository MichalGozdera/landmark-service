package eu.cokeman.cycleareastats.port.in.administrativearea;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import java.util.List;

public interface FilterAdministrativeAreaUseCase {
  List<AdministrativeArea> findByLevelAndCountry(String levelName, String countryName);

  List<AdministrativeArea> findByMetadataContains(String metadataQuery);

  List<AdministrativeArea> findSimpleByLevelAndCountry(String levelName, String countryName);

  List<AdministrativeArea> findSimpleByMetadataContains(String metadataQuery);
}
