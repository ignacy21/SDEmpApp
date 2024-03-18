package SDEmpApp.buisness.DAO;

import SDEmpApp.domain.JobAdvertisement;

import java.util.List;

public interface JobAdvertisementDAO {

    JobAdvertisement createJobAdvertisement(JobAdvertisement jobAdvertisement);

    List<JobAdvertisement> findByFormOfWork(String formOfWork);

    List<JobAdvertisement> findBySkill(String skillName);

    List<JobAdvertisement> findByLanguage(String language);
}
