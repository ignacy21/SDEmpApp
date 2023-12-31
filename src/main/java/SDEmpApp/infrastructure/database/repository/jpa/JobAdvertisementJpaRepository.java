package SDEmpApp.infrastructure.database.repository.jpa;

import SDEmpApp.domain.Localization;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import SDEmpApp.infrastructure.database.entities.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobAdvertisementJpaRepository extends JpaRepository<JobAdvertisementEntity, Integer> {

    List<JobAdvertisementEntity> findByLocalization(Localization localization);
    Optional<JobAdvertisementEntity> findByFormOfWork(String name);

    @Query("""
            SELECT jd FROM JobAdvertisementEntity jd
            JOIN jd.skillsNeeded sk WHERE sk IN :skillsNeeded
            """)
    List<JobAdvertisementEntity> findBySkillsNeeded(@Param("skillsNeeded") List<SkillEntity> skillsNeeded);

}

