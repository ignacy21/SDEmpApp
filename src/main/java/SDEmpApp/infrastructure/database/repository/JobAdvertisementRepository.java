package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.api.dto.auxiliary.enums.Experience;
import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Localization;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import SDEmpApp.infrastructure.database.entities.LocalizationEntity;
import SDEmpApp.infrastructure.database.repository.jpa.JobAdvertisementJpaRepository;
import SDEmpApp.infrastructure.database.repository.mapper.JobAdvertisementEntityMapper;
import SDEmpApp.infrastructure.database.repository.mapper.LocalizationEntityMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Repository
@AllArgsConstructor
public class JobAdvertisementRepository implements JobAdvertisementDAO {

    JobAdvertisementJpaRepository jobAdvertisementJpaRepository;

    EntityManager entityManager;

    JobAdvertisementEntityMapper jobAdvertisementEntityMapper;
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

    @Override
    public List<JobAdvertisement> findBySeniority(String seniority) {
        return jobAdvertisementJpaRepository.findBySeniority(seniority).stream()
                .map(jobAdvertisementEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<JobAdvertisement> criteriaApiFindQuery(
            Localization localization,
            String formOfWork,
            Integer experienceOrdinal,
            BigDecimal salary,
            List<String> senioritylist
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<JobAdvertisementEntity> criteriaQuery = criteriaBuilder.createQuery(JobAdvertisementEntity.class);
        Root<JobAdvertisementEntity> root = criteriaQuery.from(JobAdvertisementEntity.class);
        Predicate predicate = criteriaBuilder.conjunction();

        if (localization != null) {
            LocalizationEntity localizationEntity = localizationEntityMapper.mapToEntity(localization);
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("localization"), localizationEntity));
        }
        if (formOfWork != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("formOfWork"), formOfWork));
        }
        if (experienceOrdinal != null) {
            List<String> experiences = Arrays.stream(Experience.values())
                    .filter(x -> x.ordinal() <= experienceOrdinal)
                    .map(Experience::getExperience)
                    .toList();

            predicate = criteriaBuilder.and(predicate, root.get("experienceNeeded").in(experiences));
        }
        if (salary != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(
                    root.get("salaryTo"),
                    salary
            ));
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(
                    root.get("salaryFrom"),
                    salary
            ));
        }
        if (senioritylist != null) {
            predicate = criteriaBuilder.and(predicate, root.get("seniority").in(senioritylist));
        }

        criteriaQuery.select(root).where(predicate);
        List<JobAdvertisementEntity> resultList = entityManager.createQuery(criteriaQuery).getResultList();

        return resultList.stream()
                .map(jobAdvertisementEntityMapper::mapFromEntity)
                .toList();
    }
}
