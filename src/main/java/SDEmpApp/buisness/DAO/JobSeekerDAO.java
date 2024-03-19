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
}
