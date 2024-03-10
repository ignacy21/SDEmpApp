package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.buisness.DAO.CompanyDAO;
import SDEmpApp.buisness.DAO.LocalizationDAO;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.Localization;
import SDEmpApp.exceptions.NoSuchLocalizationException;
import SDEmpApp.infrastructure.database.entities.CompanyEntity;
import SDEmpApp.infrastructure.database.entities.LocalizationEntity;
import SDEmpApp.infrastructure.database.repository.jpa.CompanyJpaRepository;
import SDEmpApp.infrastructure.database.repository.jpa.LocalizationJpaRepository;
import SDEmpApp.infrastructure.database.repository.mapper.CompanyEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CompanyRepository implements CompanyDAO {

    CompanyJpaRepository companyJpaRepository;

    CompanyEntityMapper companyEntityMapper;


    @Override
    public Company createCompany(Company company) {
        CompanyEntity companyEntity = companyEntityMapper.mapToEntity(company);
        companyJpaRepository.saveAndFlush(companyEntity);
        return companyEntityMapper.mapFromEntity(companyEntity);
    }

    @Override
    public List<Company> findCompanyByName(String name) {
        return companyJpaRepository.findByNameContaining(name).stream()
                .map(companyEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Company findCompanyByEmailAndPassword(String email, String password) {
        Optional<CompanyEntity> company = companyJpaRepository.findByEmailAndPassword(email, password);
        return company
                .map(companyEntity -> companyEntityMapper.mapFromEntity(companyEntity))
                .orElse(null);
    }

    @Override
    public Company findCompanyById(int id) {
        Optional<CompanyEntity> byCompanyId = companyJpaRepository.findByCompanyId(id);
        CompanyEntity companyEntity = byCompanyId.orElseThrow(() ->
                new RuntimeException("There is no company with id[%s]".formatted(id)));
        return companyEntityMapper.mapFromEntity(companyEntity);
    }

}
