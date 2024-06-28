package eu.cokeman.cycleareastats.out.persistence.jpa.repository;

import eu.cokeman.cycleareastats.out.persistence.jpa.entity.LandmarkEntity;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaLandmarkRepositorySpringDataRepository extends JpaRepository<LandmarkEntity, UUID> {

    Optional<LandmarkEntity> findById(UUID landmarkID);


}