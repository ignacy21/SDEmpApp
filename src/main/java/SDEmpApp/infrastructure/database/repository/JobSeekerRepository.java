package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.api.dto.auxiliary.enums.Experience;
import SDEmpApp.buisness.DAO.JobSeekerDAO;
import SDEmpApp.domain.JobSeeker;
import SDEmpApp.domain.Localization;
import SDEmpApp.infrastructure.database.entities.JobSeekerEntity;
import SDEmpApp.infrastructure.database.entities.LocalizationEntity;
import SDEmpApp.infrastructure.database.repository.jpa.JobSeekerJpaRepository;
import SDEmpApp.infrastructure.database.repository.mapper.JobSeekerEntityMapper;
import SDEmpApp.infrastructure.database.repository.mapper.LocalizationEntityMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class JobSeekerRepository implements JobSeekerDAO {

    private JobSeekerJpaRepository jobSeekerJpaRepository;

    EntityManager entityManager;

    private JobSeekerEntityMapper jobSeekerEntityMapper;
    private LocalizationEntityMapper localizationEntityMapper;

    @Override
    public JobSeeker createJobSeeker(JobSeeker jobSeeker) {
        JobSeekerEntity jobSeekerEntity = jobSeekerEntityMapper.mapToEntity(jobSeeker);
        jobSeekerEntity.setIsEmployed(false);
        JobSeekerEntity createdJobSeekerEntity = jobSeekerJpaRepository.saveAndFlush(jobSeekerEntity);
        return jobSeeker.withJobSeekerId(createdJobSeekerEntity.getJobSeekerId());
    }

    @Override
    public JobSeeker updateJobSeekerData(JobSeeker jobSeeker) {
        JobSeekerEntity jobSeekerEntity = jobSeekerEntityMapper.mapToEntity(jobSeeker);
        jobSeekerJpaRepository.saveAndFlush(jobSeekerEntity);
        return jobSeekerEntityMapper.mapFromEntity(jobSeekerEntity);
    }

    @Override
    public JobSeeker findById(Integer jobSeekerId) {
        Optional<JobSeekerEntity> byId = jobSeekerJpaRepository.findById(jobSeekerId);
        return jobSeekerEntityMapper.mapFromEntity(byId.orElseThrow(
                () -> new RuntimeException("JobSeeker with id[%s] does not exist".formatted(jobSeekerId))
        ));

    }

    @Override
    public List<JobSeeker> findJobSeekerByUsername(String username) {
        return jobSeekerJpaRepository.findByUsernameContaining(username).stream()
                .map(jobSeekerEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<JobSeeker> findByLocalization(Localization localization) {
        return jobSeekerJpaRepository.findByLocalization(localizationEntityMapper.mapToEntity(localization)).stream()
                .map(jobSeekerEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<JobSeeker> criteriaApiFindQuery(
            Localization localization,
            Boolean isLookingForJob,
            Boolean isEmployed,
            Boolean isStudent,
            Integer experienceOrdinal
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<JobSeekerEntity> criteriaQuery = criteriaBuilder.createQuery(JobSeekerEntity.class);
        Root<JobSeekerEntity> root = criteriaQuery.from(JobSeekerEntity.class);
        Predicate predicate = criteriaBuilder.conjunction();

        if (localization != null) {
            LocalizationEntity localizationEntity = localizationEntityMapper.mapToEntity(localization);
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(
                    root.get("localization"),
                    localizationEntity
            ));
        }
        if (isLookingForJob != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(
                    root.get("lookingForJob"),
                    isLookingForJob
            ));
        }
        if (isEmployed != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(
                    root.get("isEmployed"),
                    isEmployed
            ));
        }
        if (isStudent != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(
                    root.get("isStudent"),
                    isStudent
            ));
        }
        if (experienceOrdinal != null) {
            List<String> experiences = Arrays.stream(Experience.values())
                    .filter(x -> x.ordinal() >= experienceOrdinal)
                    .map(Experience::getExperience)
                    .toList();
            predicate = criteriaBuilder.and(predicate, root.get("experience").in(experiences));
        }

        criteriaQuery.select(root).where(predicate);
        List<JobSeekerEntity> resultList = entityManager.createQuery(criteriaQuery).getResultList();

        return resultList.stream()
                .map(jobSeekerEntityMapper::mapFromEntity)
                .toList();
    }
}
