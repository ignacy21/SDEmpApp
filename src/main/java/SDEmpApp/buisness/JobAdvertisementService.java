package SDEmpApp.buisness;

import SDEmpApp.api.dto.JobAdvertisementDTO;
import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.domain.JobAdvertisement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobAdvertisementService {
    private final JobAdvertisementDAO jobAdvertisementDAO;

    public JobAdvertisement createJobAdvertisement(JobAdvertisement jobAdvertisement) {
        return jobAdvertisementDAO.createJobAdvertisement(jobAdvertisement);
    }

    public List<JobAdvertisement> findByFormOfWork(String formOfWork) {
        return jobAdvertisementDAO.findByFormOfWork(formOfWork);
    }

}
