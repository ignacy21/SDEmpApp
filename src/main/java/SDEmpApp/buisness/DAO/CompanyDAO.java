package SDEmpApp.buisness.DAO;

import SDEmpApp.domain.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyDAO {

    void createCompany(Company company);

    List<Company> findByNameContaining(String name);

    Optional<Company> findByCompanyId(Integer companyId);
}
