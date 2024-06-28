package eu.cokeman.cycleareastats;

import eu.cokeman.cycleareastats.port.in.FetchLandmarkUseCase;
import eu.cokeman.cycleareastats.port.in.ImportLandmarkUseCase;
import eu.cokeman.cycleareastats.port.out.persistence.LandmarkRepository;

import eu.cokeman.cycleareastats.port.out.persistence.LandmarksRepository;
import eu.cokeman.cycleareastats.service.landmark.FetchLandmarkService;
import eu.cokeman.cycleareastats.service.landmark.ImportLandmarkService;
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


  @Autowired LandmarkRepository landmarkRepository;

  LandmarksRepository landmarksRepository;


  @Bean
  FetchLandmarkUseCase fetchLandmarkUseCase() {
    return new FetchLandmarkService(landmarkRepository);
  }

  @Bean
  ImportLandmarkUseCase importLandmarkUseCase() {
    return new ImportLandmarkService(landmarkRepository);
  }

  //todo add services
}
