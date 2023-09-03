package SDEmpApp.buisness.DAO;

import SDEmpApp.domain.Company;

public interface CompanyDAO {

    void createCompany(Company company);

    Company findCompanyByName();
}
