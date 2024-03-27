package SDEmpApp.infrastructure.database.repository.jpa;

import SDEmpApp.infrastructure.database.entities.JobSeekerEntity;
import SDEmpApp.infrastructure.database.entities.LocalizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerJpaRepository extends JpaRepository<JobSeekerEntity, Integer> {

    List<JobSeekerEntity> findByUsernameContaining(String username);
    List<JobSeekerEntity> findJobSeekerEntitiesByIsStudent(Boolean isStudent);
    List<JobSeekerEntity> findByLanguagesContaining(String language);
    List<JobSeekerEntity> findBySkillsContaining(String skill);
    List<JobSeekerEntity> findByFormsOfEmployment(String formOfEmployment);
    List<JobSeekerEntity> findByFormsOfWork(String formsOfWork);
    List<JobSeekerEntity> findByExperience(String experience);
    List<JobSeekerEntity> findJobSeekerEntitiesByIsEmployed(Boolean isEmployed);
    List<JobSeekerEntity> findJobSeekerEntitiesByLookingForJob(Boolean lookingForJob);
    List<JobSeekerEntity> findByLocalization(LocalizationEntity localization);
}
