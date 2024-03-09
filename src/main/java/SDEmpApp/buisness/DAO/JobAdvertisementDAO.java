package SDEmpApp.buisness.DAO;

import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Localization;

import java.util.List;

public interface JobAdvertisementDAO {

    JobAdvertisement createJobAdvertisement(JobAdvertisement jobAdvertisement);

    List<Company> findCompanyByFormOfWork(String value);

//    List<Company> findCompanyBySkillsNeeded(List<String> skillList);

    List<Company> findCompanyByLocalization(Localization localization);
}
