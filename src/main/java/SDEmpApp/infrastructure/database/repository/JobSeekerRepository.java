package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.buisness.DAO.JobSeekerDAO;
import SDEmpApp.domain.JobSeeker;
import SDEmpApp.infrastructure.database.entities.JobSeekerEntity;
import SDEmpApp.infrastructure.database.repository.jpa.JobSeekerJpaRepository;
import SDEmpApp.infrastructure.database.repository.mapper.JobSeekerEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
