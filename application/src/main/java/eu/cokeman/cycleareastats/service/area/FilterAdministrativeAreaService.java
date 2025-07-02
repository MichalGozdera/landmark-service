package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.port.in.administrativearea.FilterAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;

import java.util.List;

public class FilterAdministrativeAreaService implements FilterAdministrativeAreaUseCase {

    private final AdministrativeAreaRepository administrativeAreaRepository;

    public FilterAdministrativeAreaService(AdministrativeAreaRepository administrativeAreaRepository) {
        this.administrativeAreaRepository = administrativeAreaRepository;
    }

    @Override
    public List<AdministrativeArea> findByLevelAndCountry(String levelName, String countryName) {
        return administrativeAreaRepository.findByLevelAndCountry(levelName, countryName);
    }

    @Override
    public List<AdministrativeArea> findByMetadataContains(String metadataQuery) {
        return administrativeAreaRepository.findByMetadataContains(metadataQuery);
    }

    @Override
    public List<AdministrativeArea> findSimpleByLevelAndCountry(String levelName, String countryName) {
        return administrativeAreaRepository.findSimpleByLevelAndCountry(levelName, countryName);
    }

    @Override
    public List<AdministrativeArea> findSimpleByMetadataContains(String metadataQuery) {
        return administrativeAreaRepository.findSimpleByMetadataContains(metadataQuery);
    }

}
