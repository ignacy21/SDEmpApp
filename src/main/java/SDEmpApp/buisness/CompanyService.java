package SDEmpApp.buisness;

import SDEmpApp.buisness.DAO.CompanyDAO;
import SDEmpApp.domain.Company;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyDAO companyDAO;

    @Transactional
    public Company createCompany(Company company) {
        // TODO if company data is already existing
        return companyDAO.createCompany(company);
    }
    public void updateCompany(Company company) {
        companyDAO.updateCompany(company);
    }

    public Company findCompanyById(int id) {
        return companyDAO.findCompanyById(id);
    }

}
