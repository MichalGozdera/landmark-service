package eu.cokeman.cycleareastats;

import eu.cokeman.cycleareastats.converters.kml.KmlParser;
import eu.cokeman.cycleareastats.port.in.administrativearea.*;
import eu.cokeman.cycleareastats.port.in.administrativelevel.CreateAdministrativeLevelUseCase;
import eu.cokeman.cycleareastats.port.in.administrativelevel.DeleteAdministrativeLevelUseCase;
import eu.cokeman.cycleareastats.port.in.administrativelevel.FetchAdministrativeLevelUseCase;
import eu.cokeman.cycleareastats.port.in.administrativelevel.UpdateAdministrativeLevelUseCase;
import eu.cokeman.cycleareastats.port.in.country.CreateCountryUseCase;
import eu.cokeman.cycleareastats.port.in.country.DeleteCountryUseCase;
import eu.cokeman.cycleareastats.port.in.country.FetchCountryUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeAreaRepository;

import eu.cokeman.cycleareastats.port.out.persistence.AdministrativeLevelRepository;
import eu.cokeman.cycleareastats.port.out.persistence.CountryRepository;
import eu.cokeman.cycleareastats.port.out.publishing.AdministrativeAreaPublisher;
import eu.cokeman.cycleareastats.service.area.*;
import eu.cokeman.cycleareastats.service.country.CreateCountryService;
import eu.cokeman.cycleareastats.service.country.DeleteCountryService;
import eu.cokeman.cycleareastats.service.country.FetchCountryService;
import eu.cokeman.cycleareastats.service.level.CreateAdministrativeLevelService;
import eu.cokeman.cycleareastats.service.level.DeleteAdministrativeLevelService;
import eu.cokeman.cycleareastats.service.level.FetchAdministrativeLevelService;
import eu.cokeman.cycleareastats.service.level.UpdateAdministrativeLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Spring application configuration, making Spring beans from services defined in application
 * module.
 *
 * @author Sven Woltmann
 */
@SpringBootApplication

public class SpringAppConfig {


    @Autowired
    AdministrativeAreaRepository administrativeAreaRepository;

    @Autowired
    AdministrativeLevelRepository administrativeLevelRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    AdministrativeAreaPublisher publisher;


    @Bean
    FetchAdministrativeAreaUseCase fetchLandmarkUseCase() {
        return new FetchAdministrativeAreaService(administrativeAreaRepository);
    }

    @Bean
    UpdateAdministrativeAreaUseCase updateAdministrativeAreaUseCase() {
        return new UpdateAdministrativeAreaService(administrativeAreaRepository, administrativeLevelRepository);
    }

    @Bean
    DeleteAdministrativeAreaUseCase deleteAdministrativeLevelUseCase() {
        return new DeleteAdministrativeAreaService(administrativeAreaRepository);
    }

    @Bean
    ConvertAdministrativeAreaGeometryUseCase convertLandmarkGeometryUseCase() {
        return new KmlParser();
    }


    @Bean
    ImportAdministrativeAreaUseCase importLandmarkUseCase() {
        return new ImportAdministrativeAreaService(administrativeAreaRepository, administrativeLevelRepository, publisher, convertLandmarkGeometryUseCase());
    }

    @Bean
    CreateAdministrativeLevelUseCase createAdministrativeLevelUseCase() {
        return new CreateAdministrativeLevelService(administrativeLevelRepository, countryRepository);
    }

    @Bean
    FetchAdministrativeLevelUseCase fetchLevelUseCase() {
        return new FetchAdministrativeLevelService(administrativeLevelRepository);
    }

    @Bean
    UpdateAdministrativeLevelUseCase updateLevelUseCase() {
        return new UpdateAdministrativeLevelService(administrativeLevelRepository, countryRepository);
    }

    @Bean
    DeleteAdministrativeLevelUseCase deleteLevelUseCase() {
        return new DeleteAdministrativeLevelService(administrativeLevelRepository);
    }

    @Bean
    CreateCountryUseCase createCountryUseCase() {
        return new CreateCountryService(countryRepository);
    }

    @Bean
    FetchCountryUseCase fetchCountryUseCase() {
        return new FetchCountryService(countryRepository);
    }

    @Bean
    DeleteCountryUseCase deleteCountryUseCase() {
        return new DeleteCountryService(countryRepository);
    }

}
