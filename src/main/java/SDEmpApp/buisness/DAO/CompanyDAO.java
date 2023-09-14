package SDEmpApp.buisness.DAO;

import SDEmpApp.domain.Company;

import java.util.List;

public interface CompanyDAO {

    void createCompany(Company company);

    List<Company> findCompanyByName(String name);
}
