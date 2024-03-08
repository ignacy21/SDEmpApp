package SDEmpApp.buisness.DAO;

import SDEmpApp.domain.Company;

import java.util.List;

public interface CompanyDAO {

    Company createCompany(Company company);
    default void updateCompany(Company company) {
        createCompany(company);
    }

    List<Company> findCompanyByName(String name);

    Company findCompanyByEmailAndPassword(String email, String password);

    Company findCompanyById(int id);
}
