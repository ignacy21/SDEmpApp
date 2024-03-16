package SDEmpApp.infrastructure.database.repository.jpa;

import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobAdvertisementJpaRepository extends JpaRepository<JobAdvertisementEntity, Integer> {

    List<JobAdvertisementEntity> findByFormOfWork(String name);
//    List<JobAdvertisementEntity> findBySkillNeeded(String name);
    List<JobAdvertisementEntity> findBySkillsNeededContaining(String skills);


}

