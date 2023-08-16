package SDEmpApp.infrastructure.database.repository.jpa;

import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobAdvertisementJpaRepository extends JpaRepository<JobAdvertisementEntity, Integer> {

    Optional<JobAdvertisementEntity> findByLocalization(String name);
    Optional<JobAdvertisementEntity> findBySkillsNeeded(String name);
    Optional<JobAdvertisementEntity> findByFormOfWork(String name);

}

