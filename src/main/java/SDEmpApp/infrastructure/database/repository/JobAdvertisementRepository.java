package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.infrastructure.database.entities.CompanyEntity;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import SDEmpApp.infrastructure.database.repository.jpa.JobAdvertisementJpaRepository;
import SDEmpApp.infrastructure.database.repository.mapper.CompanyEntityMapper;
import SDEmpApp.infrastructure.database.repository.mapper.JobAdvertisementEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class JobAdvertisementRepository implements JobAdvertisementDAO {

    JobAdvertisementJpaRepository jobAdvertisementJpaRepository;

    JobAdvertisementEntityMapper jobAdvertisementEntityMapper;
    CompanyEntityMapper companyEntityMapper;

    @Override
    public JobAdvertisement createJobAdvertisement(JobAdvertisement jobAdvertisement) {
        jobAdvertisementEntityMapper.mapToEntity(jobAdvertisement);

        JobAdvertisementEntity jobAdvertEntity = jobAdvertisementEntityMapper.mapToEntity(jobAdvertisement);

        CompanyEntity companyEntity = companyEntityMapper.mapToEntity(jobAdvertisement.getCompany());
        jobAdvertEntity.setCompany(companyEntity);

        JobAdvertisementEntity jobAdvertEntityCreated = jobAdvertisementJpaRepository.saveAndFlush(jobAdvertEntity);
        jobAdvertisement.setJobAdvertisementId(jobAdvertEntityCreated.getJobAdvertisementId());
        return jobAdvertisement;
    }
}
