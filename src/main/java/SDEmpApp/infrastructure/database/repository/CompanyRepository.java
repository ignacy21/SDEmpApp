package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.buisness.DAO.CompanyDAO;
import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.infrastructure.database.entities.CompanyEntity;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import SDEmpApp.infrastructure.database.repository.jpa.CompanyJpaRepository;
import SDEmpApp.infrastructure.database.repository.jpa.JobAdvertisementJpaRepository;
import SDEmpApp.infrastructure.database.repository.mapper.CompanyEntityMapper;
import SDEmpApp.infrastructure.database.repository.mapper.JobAdvertisementEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CompanyRepository implements CompanyDAO, JobAdvertisementDAO {

    CompanyJpaRepository companyJpaRepository;
    JobAdvertisementJpaRepository jobAdvertisementJpaRepository;

    CompanyEntityMapper companyEntityMapper;
    JobAdvertisementEntityMapper jobAdvertisementEntityMapper;


    @Override
    public void createCompany(Company company) {
        CompanyEntity companyEntity = companyEntityMapper.mapToEntity(company);
        companyJpaRepository.saveAndFlush(companyEntity);
    }


    @Override
    public void createJobAdvertisement(JobAdvertisement jobAdvertisement) {
        JobAdvertisementEntity jobAdvertisementEntity = jobAdvertisementEntityMapper.mapToEntity(jobAdvertisement);
        jobAdvertisementJpaRepository.saveAndFlush(jobAdvertisementEntity);
    }
}
