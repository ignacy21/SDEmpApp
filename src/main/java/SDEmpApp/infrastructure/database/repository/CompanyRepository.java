package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.buisness.DAO.CompanyDAO;
import SDEmpApp.domain.Company;
import SDEmpApp.infrastructure.database.entities.CompanyEntity;
import SDEmpApp.infrastructure.database.repository.jpa.CompanyJpaRepository;
import SDEmpApp.infrastructure.database.repository.mapper.CompanyEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class CompanyRepository implements CompanyDAO {

    CompanyJpaRepository companyJpaRepository;
    CompanyEntityMapper companyEntityMapper;


    @Override
    public void createCompany(Company company) {
        CompanyEntity companyEntity = companyEntityMapper.mapToEntity(company);
        companyJpaRepository.saveAndFlush(companyEntity);
    }

    @Override
    public List<Company> findCompanyByName(String name) {
        return companyJpaRepository.findByNameContaining(name).stream()
                .map(companyEntityMapper::mapFromEntity)
                .toList();
    }

}
