package eu.cokeman.cycleareastats.port.in.administrativearea;

import eu.cokeman.cycleareastats.entity.AdministrativeArea;
import eu.cokeman.cycleareastats.entity.Country;

import java.util.List;

public interface FilterAdministrativeAreaUseCase {

    List<AdministrativeArea> filterByCountry(Country country);
}
