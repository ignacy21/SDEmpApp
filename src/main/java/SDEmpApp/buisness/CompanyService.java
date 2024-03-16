package SDEmpApp.buisness;

import SDEmpApp.buisness.DAO.CompanyDAO;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.Localization;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyDAO companyDAO;

    @Transactional
    public Company createCompany(Company company) {
        // TODO if company data is already existing
        return companyDAO.createCompany(company);
    }
    @Transactional
    public void updateCompany(Company company) {
        companyDAO.updateCompany(company);
    }

    @Transactional
    public Company findCompanyById(int id) {
        return companyDAO.findCompanyById(id);
    }

    public List<Company> findCompanyByName(String companyName) {
        return companyDAO.findCompanyByName(companyName);
    }

    public List<Company> findByLocalization(Localization localization) {
        return companyDAO.findCompanyByLocalization(localization);
    }
}
