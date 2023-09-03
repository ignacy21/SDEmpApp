package SDEmpApp.buisness.DAO;

import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;

import java.util.List;

public interface JobAdvertisementDAO {

    void createJobAdvertisement(JobAdvertisement jobAdvertisement);

    List<Company> findCompanyByLocalization();

    List<Company> findCompanyBySkillsNeeded();

    List<Company> findCompanyByFormOfWork();
}
