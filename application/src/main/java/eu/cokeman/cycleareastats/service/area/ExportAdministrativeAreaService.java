package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.port.in.administrativearea.AdministrativeAreaConverter;
import eu.cokeman.cycleareastats.port.in.administrativearea.ExportAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaGeometry;


public class ExportAdministrativeAreaService implements ExportAdministrativeAreaUseCase {
    private final AdministrativeAreaRepository administrativeAreaRepository;
    private final AdministrativeAreaConverter converter;

    public ExportAdministrativeAreaService(AdministrativeAreaRepository administrativeAreaRepository, AdministrativeAreaConverter klmConverter) {
        this.administrativeAreaRepository = administrativeAreaRepository;
        this.converter = klmConverter;
    }

    @Override
    public String exportKmlByCountryAndLevel(String countryName, String levelName) {
        var areas = administrativeAreaRepository.findByLevelAndCountry(levelName, countryName);
        return converter.convertToKml(areas.stream().map(r->new AdministrativeAreaGeometry(r.getName().name(), r.getGeometry())).toList());
    }
}
