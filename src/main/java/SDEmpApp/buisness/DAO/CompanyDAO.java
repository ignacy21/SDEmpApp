package SDEmpApp.buisness.DAO;

import SDEmpApp.domain.Company;
import SDEmpApp.infrastructure.database.entities.CompanyEntity;

import java.util.List;

public interface CompanyDAO {

    CompanyEntity createCompany(Company company);

    List<Company> findCompanyByName(String name);

    Company findCompanyByEmailAndPassword(String email, String password);

    Company findCompanyById(int id);
}
