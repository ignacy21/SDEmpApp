package SDEmpApp.buisness.DAO;

import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Localization;
import SDEmpApp.domain.Skill;

import java.util.Collection;
import java.util.List;

public interface JobAdvertisementDAO {

    void createJobAdvertisement(JobAdvertisement jobAdvertisement);

    List<Company> findCompanyByFormOfWork(String value);

    List<Company> findCompanyBySkillsNeeded(List<Skill> skillList);

    List<Company> findCompanyByLocalization(Localization localization);
}
