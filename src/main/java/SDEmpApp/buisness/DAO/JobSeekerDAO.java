package SDEmpApp.buisness.DAO;

import SDEmpApp.domain.JobSeeker;

import java.util.List;

public interface JobSeekerDAO {

    JobSeeker createJobSeeker(JobSeeker jobSeeker);

    JobSeeker updateJobSeekerData(JobSeeker jobSeeker);

    JobSeeker findById(Integer jobSeekerId);

    List<JobSeeker> findJobSeekerByUsername(String username);

    List<JobSeeker> findStudents(Boolean isStudent);

    List<JobSeeker> findByLanguage(String language);

    List<JobSeeker> findBySkill(String skill);

    List<JobSeeker> findAll();

    List<JobSeeker> findByFormOfEmployment(String formOfEmployment);

    List<JobSeeker> findByFormOfWork(String formOfWork);

    List<JobSeeker> findByExperience(String experience);

    List<JobSeeker> findIfIsEmployed(Boolean isEmployed);

    List<JobSeeker> findIfIsLookingForJob(Boolean isLookingForJob);
}
