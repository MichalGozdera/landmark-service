package eu.cokeman.cycleareastats.service.area;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.port.in.administrativearea.FilterAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;
import eu.cokeman.cycleareastats.valueObject.Country;

import java.util.List;

public class FilterAdministrativeAreaService implements FilterAdministrativeAreaUseCase {

    private final AdministrativeAreaRepository administrativeAreaRepository;

    public FilterAdministrativeAreaService(AdministrativeAreaRepository administrativeAreaRepository) {
        this.administrativeAreaRepository = administrativeAreaRepository;
    }

    @Override
    public List<AdministrativeArea> filterByCountry(Country country) {
      return administrativeAreaRepository.findByCountry(country);
    }
}
