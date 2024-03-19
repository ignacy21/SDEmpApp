package SDEmpApp.infrastructure.database.repository.jpa;

import SDEmpApp.infrastructure.database.entities.JobSeekerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerJpaRepository extends JpaRepository<JobSeekerEntity, Integer> {

    List<JobSeekerEntity> findByUsernameContaining(String username);
    List<JobSeekerEntity> findJobSeekerEntitiesByIsStudent(Boolean isStudent);
    List<JobSeekerEntity> findByLanguagesContaining(String language);

    List<JobSeekerEntity> findBySkillsContaining(String skill);
}
