package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.buisness.DAO.JobSeekerDAO;
import SDEmpApp.domain.JobSeeker;
import SDEmpApp.infrastructure.database.entities.JobSeekerEntity;
import SDEmpApp.infrastructure.database.repository.jpa.JobSeekerJpaRepository;
import SDEmpApp.infrastructure.database.repository.mapper.JobSeekerEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class JobSeekerRepository implements JobSeekerDAO {

    private JobSeekerJpaRepository jobSeekerJpaRepository;
    private JobSeekerEntityMapper jobSeekerEntityMapper;

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
    public List<JobSeeker> findStudents(Boolean isStudent) {
        List<JobSeekerEntity> studentEntities = jobSeekerJpaRepository.findJobSeekerEntitiesByIsStudent(isStudent);
        return studentEntities.stream().map(jobSeekerEntityMapper::mapFromEntity).toList();
    }

    @Override
    public List<JobSeeker> findByLanguage(String language) {
        List<JobSeekerEntity> byLanguage =  jobSeekerJpaRepository.findByLanguagesContaining(language);
        return byLanguage.stream().map(jobSeekerEntityMapper::mapFromEntity).toList();
    }

    @Override
    public List<JobSeeker> findBySkill(String skill) {
        List<JobSeekerEntity> jobSeekerEntities = jobSeekerJpaRepository.findBySkillsContaining(skill);
        return jobSeekerEntities.stream().map(jobSeekerEntityMapper::mapFromEntity).toList();
    }

    @Override
    public List<JobSeeker> findAll() {
        return jobSeekerJpaRepository.findAll().stream().map(jobSeekerEntityMapper::mapFromEntity).toList();
    }

    @Override
    public List<JobSeeker> findByFormOfEmployment(String formOfEmployment) {
        return jobSeekerJpaRepository.findByB2bNormalFit(formOfEmployment).stream()
                .map(jobSeekerEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<JobSeeker> findByFormOfWork(String formOfWork) {
        return jobSeekerJpaRepository.findByFormOfWork(formOfWork).stream()
                .map(jobSeekerEntityMapper::mapFromEntity)
                .toList();

    }

    @Override
    public List<JobSeeker> findByExperience(String experience) {
        return jobSeekerJpaRepository.findByExperience(experience).stream()
                .map(jobSeekerEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<JobSeeker> findIfIsEmployed(Boolean isEmployed) {
        return jobSeekerJpaRepository.findJobSeekerEntitiesByIsEmployed(isEmployed).stream()
                .map(jobSeekerEntityMapper::mapFromEntity)
                .toList();
    }
}
