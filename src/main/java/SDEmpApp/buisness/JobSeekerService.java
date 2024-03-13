package SDEmpApp.buisness;

import SDEmpApp.buisness.DAO.JobSeekerDAO;
import SDEmpApp.domain.JobSeeker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class JobSeekerService {

    private final JobSeekerDAO jobSeekerDAO;

    @Transactional
    public JobSeeker createJobSeeker(JobSeeker jobSeeker) {
        // TODO if jobSeeker's data is already existing
        return jobSeekerDAO.createJobSeeker(jobSeeker);
    }

    public JobSeeker updateJobSeekerData(JobSeeker jobSeeker) {
        return jobSeekerDAO.updateJobSeekerData(jobSeeker);
    }

    public JobSeeker findById(Integer jobSeekerId) {
        return jobSeekerDAO.findById(jobSeekerId);
    }

}
