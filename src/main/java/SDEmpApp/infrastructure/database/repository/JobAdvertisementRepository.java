package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Localization;
import SDEmpApp.infrastructure.database.entities.CompanyEntity;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import SDEmpApp.infrastructure.database.entities.LocalizationEntity;
import SDEmpApp.infrastructure.database.repository.jpa.JobAdvertisementJpaRepository;
import SDEmpApp.infrastructure.database.repository.mapper.CompanyEntityMapper;
import SDEmpApp.infrastructure.database.repository.mapper.JobAdvertisementEntityMapper;
import SDEmpApp.infrastructure.database.repository.mapper.LocalizationEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class JobAdvertisementRepository implements JobAdvertisementDAO {

    JobAdvertisementJpaRepository jobAdvertisementJpaRepository;

    JobAdvertisementEntityMapper jobAdvertisementEntityMapper;
    CompanyEntityMapper companyEntityMapper;
    LocalizationEntityMapper localizationEntityMapper;

    @Override
    public JobAdvertisement createJobAdvertisement(JobAdvertisement jobAdvertisement) {
        Localization localization = jobAdvertisement.getLocalization();
        JobAdvertisementEntity jobAdvertEntity = jobAdvertisementEntityMapper.mapToEntity(jobAdvertisement);

        CompanyEntity companyEntity = companyEntityMapper.mapToEntity(jobAdvertisement.getCompany());
        jobAdvertEntity.setCompany(companyEntity);

        JobAdvertisementEntity jobAdvertEntityCreated = jobAdvertisementJpaRepository.saveAndFlush(jobAdvertEntity);
        jobAdvertisement.setJobAdvertisementId(jobAdvertEntityCreated.getJobAdvertisementId());
        return jobAdvertisement;
    }
}
