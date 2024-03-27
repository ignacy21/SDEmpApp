package SDEmpApp.buisness.DAO;

import SDEmpApp.domain.JobSeeker;
import SDEmpApp.domain.Localization;

import java.util.List;

public interface JobSeekerDAO {

    JobSeeker createJobSeeker(JobSeeker jobSeeker);

    JobSeeker updateJobSeekerData(JobSeeker jobSeeker);

    JobSeeker findById(Integer jobSeekerId);

    List<JobSeeker> findJobSeekerByUsername(String username);

    List<JobSeeker> findByLocalization(Localization localization);

    List<JobSeeker> criteriaApiFindQuery(
            Localization localization,
            Boolean isLookingForJob,
            Boolean isEmployed,
            Boolean isStudent,
            Integer experienceOrdinal
    );

}
