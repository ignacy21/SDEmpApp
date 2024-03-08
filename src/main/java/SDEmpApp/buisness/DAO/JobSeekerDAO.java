package SDEmpApp.buisness.DAO;

import SDEmpApp.domain.JobSeeker;

public interface JobSeekerDAO {

    JobSeeker createJobSeeker(JobSeeker jobSeeker);

    void updateJobSeekerData(JobSeeker jobSeeker);

    JobSeeker findById(Integer jobSeekerId);
}
