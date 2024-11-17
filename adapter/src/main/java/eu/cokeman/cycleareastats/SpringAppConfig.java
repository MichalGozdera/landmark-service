package eu.cokeman.cycleareastats;

import eu.cokeman.cycleareastats.converters.kml.KmlParser;
import eu.cokeman.cycleareastats.port.in.ConvertLandmarkGeometryUseCase;
import eu.cokeman.cycleareastats.port.in.FetchLandmarkUseCase;
import eu.cokeman.cycleareastats.port.in.ImportLandmarkUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.LandmarkRepository;

import eu.cokeman.cycleareastats.port.out.publishing.LandmarkPublisher;
import eu.cokeman.cycleareastats.service.landmark.FetchLandmarkService;
import eu.cokeman.cycleareastats.service.landmark.ImportLandmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring application configuration, making Spring beans from services defined in application
 * module.
 *
 * @author Sven Woltmann
 */
@SpringBootApplication

public class SpringAppConfig {


    @Autowired
    LandmarkRepository landmarkRepository;

    @Autowired
    LandmarkPublisher publisher;


    @Bean
    FetchLandmarkUseCase fetchLandmarkUseCase() {
        return new FetchLandmarkService(landmarkRepository);
    }

    @Bean
    ConvertLandmarkGeometryUseCase convertLandmarkGeometryUseCase() {
        return new KmlParser();
    }

    @Bean
    ImportLandmarkUseCase importLandmarkUseCase() {
        return new ImportLandmarkService(landmarkRepository, publisher, convertLandmarkGeometryUseCase());
    }


    //todo add services
}
