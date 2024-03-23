package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Localization;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import SDEmpApp.infrastructure.database.repository.jpa.JobAdvertisementJpaRepository;
import SDEmpApp.infrastructure.database.repository.mapper.CompanyEntityMapper;
import SDEmpApp.infrastructure.database.repository.mapper.JobAdvertisementEntityMapper;
import SDEmpApp.infrastructure.database.repository.mapper.LocalizationEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@AllArgsConstructor
public class JobAdvertisementRepository implements JobAdvertisementDAO {

    JobAdvertisementJpaRepository jobAdvertisementJpaRepository;

    JobAdvertisementEntityMapper jobAdvertisementEntityMapper;
    CompanyEntityMapper companyEntityMapper;
    LocalizationEntityMapper localizationEntityMapper;

    @Override
    public JobAdvertisement createJobAdvertisement(JobAdvertisement jobAdvertisement) {
        JobAdvertisementEntity jobAdvertEntity = jobAdvertisementEntityMapper.mapToEntity(jobAdvertisement);

        JobAdvertisementEntity jobAdvertEntityCreated = jobAdvertisementJpaRepository.saveAndFlush(jobAdvertEntity);
        jobAdvertisement.setJobAdvertisementId(jobAdvertEntityCreated.getJobAdvertisementId());
        return jobAdvertisement;
    }

    @Override
    public List<JobAdvertisement> findByFormOfWork(String formOfWork) {
        List<JobAdvertisementEntity> byFormOfWork = jobAdvertisementJpaRepository.findByFormOfWork(formOfWork);
        return byFormOfWork.stream().map(jobAdvertisementEntityMapper::mapFromEntity).toList();
    }

    @Override
    public List<JobAdvertisement> findBySkill(String skillName) {
        List<JobAdvertisementEntity> bySkill = jobAdvertisementJpaRepository.findBySkillsNeededContaining(skillName);
        return bySkill.stream().map(jobAdvertisementEntityMapper::mapFromEntity).toList();
    }

    @Override
    public List<JobAdvertisement> findByLanguage(String language) {
        List<JobAdvertisementEntity> byLanguage = jobAdvertisementJpaRepository.findByLanguagesContaining(language);
        return byLanguage.stream().map(jobAdvertisementEntityMapper::mapFromEntity).toList();
    }

    @Override
    public List<JobAdvertisement> findByLocalization(Localization localization) {
        List<JobAdvertisementEntity> byLocalization = jobAdvertisementJpaRepository.findByLocalization(
                localizationEntityMapper.mapToEntity(localization)
        );
        return byLocalization.stream().map(jobAdvertisementEntityMapper::mapFromEntity).toList();
    }

    @Override
    public JobAdvertisement findById(Integer jobAdvertisementId) {
        JobAdvertisementEntity jobAdvertisementEntity = jobAdvertisementJpaRepository
                .findByJobAdvertisementId(jobAdvertisementId).orElseThrow(
                        () -> new RuntimeException("there is no such JobAdvertisement with id[%s]"
                                .formatted(jobAdvertisementId)));

        return jobAdvertisementEntityMapper.mapFromEntity(jobAdvertisementEntity);
    }

    @Override
    public List<JobAdvertisement> findByExperience(String experienceNeeded) {
        return jobAdvertisementJpaRepository.findByExperienceNeeded(experienceNeeded).stream()
                .map(jobAdvertisementEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<JobAdvertisement> isSalaryBetweenRequiredSalary(BigDecimal salary) {
        return jobAdvertisementJpaRepository.findBySalaryFromGreaterThanEqualOrSalaryToGreaterThanEqual(salary, salary).stream()
                .map(jobAdvertisementEntityMapper::mapFromEntity)
                .toList();
    }
}
