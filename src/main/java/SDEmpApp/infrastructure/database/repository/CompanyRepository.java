package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.buisness.DAO.CompanyDAO;
import SDEmpApp.buisness.DAO.LocalizationDAO;
import SDEmpApp.domain.Company;
import SDEmpApp.exceptions.NoSuchCompanyException;
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
    LocalizationJpaRepository localizationJpaRepository;
    CompanyEntityMapper companyEntityMapper;

    LocalizationDAO localizationDAO;

    @Override
    public void createCompany(Company company) {
        CompanyEntity companyEntity = companyEntityMapper.mapToEntity(company);
        Optional<LocalizationEntity> byProvinceNameAndCityName = localizationJpaRepository.findByProvinceNameAndCityName(
                company.getLocalization().getProvinceName(),
                company.getLocalization().getCityName()
        );
        if (byProvinceNameAndCityName.isEmpty()) {
            throw new NoSuchLocalizationException(company);
        }
        companyEntity.setLocalizationId(byProvinceNameAndCityName.get().getLocalizationId());
        companyJpaRepository.saveAndFlush(companyEntity);
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
        CompanyEntity companyEntity = company.orElseThrow(NoSuchCompanyException::new);
        return companyEntityMapper.mapFromEntity(companyEntity);
    }

}
