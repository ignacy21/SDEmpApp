package SDEmpApp.buisness.DAO;

import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Localization;

import java.util.List;

public interface JobAdvertisementDAO {

    JobAdvertisement createJobAdvertisement(JobAdvertisement jobAdvertisement);

    List<JobAdvertisement> findByFormOfWork(String formOfWork);

    List<JobAdvertisement> findBySkill(String skillName);

    List<JobAdvertisement> findByLanguage(String language);

    List<JobAdvertisement> findByLocalization(Localization localization);

    JobAdvertisement findById(Integer jobAdvertisementId);

    default JobAdvertisement updateJobAdvertisement(JobAdvertisement jobAdvertisement) {
        return createJobAdvertisement(jobAdvertisement);
    }

    List<JobAdvertisement> findByExperience(String experienceNeeded);
}
