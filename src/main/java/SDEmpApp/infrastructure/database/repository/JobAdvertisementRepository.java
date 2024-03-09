package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Localization;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import SDEmpApp.infrastructure.database.repository.jpa.JobAdvertisementJpaRepository;
import SDEmpApp.infrastructure.database.repository.mapper.JobAdvertisementEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class JobAdvertisementRepository implements JobAdvertisementDAO {

    JobAdvertisementJpaRepository jobAdvertisementJpaRepository;
    LocalizationRepository localizationRepository;

    JobAdvertisementEntityMapper jobAdvertisementEntityMapper;

    @Override
    public JobAdvertisement createJobAdvertisement(JobAdvertisement jobAdvertisement) {
        JobAdvertisementEntity jobAdvertEntity = jobAdvertisementEntityMapper.mapToEntity(jobAdvertisement);
        Localization localization = jobAdvertisement.getLocalization();
        Localization checkIfLocalizationIsCorrect = localizationRepository.findLocalizationByProvinceAndCity(
                localization.getProvinceName(),
                localization.getCityName()
        );
        jobAdvertEntity.setLocalizationId(checkIfLocalizationIsCorrect.getLocalizationId());
        JobAdvertisementEntity jobAdvertEntityCreated = jobAdvertisementJpaRepository.saveAndFlush(jobAdvertEntity);
        jobAdvertisement.setJobAdvertisementId(jobAdvertEntityCreated.getJobAdvertisementId());
        return jobAdvertisement;
    }

    @Override
    public List<Company> findCompanyByFormOfWork(String name) {
        return jobAdvertisementJpaRepository.findByFormOfWork(name).stream()
                .map(jobAdvertisementEntityMapper::mapFromEntity)
                .map(JobAdvertisement::getCompany)
                .toList();
    }

    @Override
    public List<Company> findCompanyByLocalization(Localization localization) {
        return jobAdvertisementJpaRepository.findByLocalization(localization).stream()
                .map(jobAdvertisementEntityMapper::mapFromEntity)
                .map(JobAdvertisement::getCompany)
                .toList();
    }
}
