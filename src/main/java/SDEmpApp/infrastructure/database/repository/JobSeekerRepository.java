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
}
