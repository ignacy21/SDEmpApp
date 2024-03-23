package SDEmpApp.infrastructure.database.repository.jpa;

import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import SDEmpApp.infrastructure.database.entities.LocalizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobAdvertisementJpaRepository extends JpaRepository<JobAdvertisementEntity, Integer> {

    List<JobAdvertisementEntity> findByFormOfWork(String name);
    List<JobAdvertisementEntity> findBySkillsNeededContaining(String skills);
    List<JobAdvertisementEntity> findByLanguagesContaining(String language);
    List<JobAdvertisementEntity> findByLocalization(LocalizationEntity language);
    Optional<JobAdvertisementEntity> findByJobAdvertisementId(Integer jobAdvertisementOd);
    List<JobAdvertisementEntity> findByExperienceNeeded(String experience);

}

