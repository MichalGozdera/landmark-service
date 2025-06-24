package eu.cokeman.cycleareastats.port.in.administrativearea;


public interface ExportAdministrativeAreaUseCase {
    String exportKmlByCountryAndLevel(String countryName, String levelName);
}

