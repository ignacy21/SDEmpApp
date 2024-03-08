package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.buisness.DAO.JobSeekerDAO;
import SDEmpApp.domain.JobSeeker;
import SDEmpApp.infrastructure.database.entities.JobSeekerEntity;
import SDEmpApp.infrastructure.database.repository.jpa.JobSeekerJpaRepository;
import SDEmpApp.infrastructure.database.repository.mapper.CandidateEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class JobSeekerRepository implements JobSeekerDAO {

    private JobSeekerJpaRepository jobSeekerJpaRepository;
    private CandidateEntityMapper candidateEntityMapper;

    @Override
    public void createJobSeeker(JobSeeker jobSeeker) {
        JobSeekerEntity jobSeekerEntity = candidateEntityMapper.mapToEntity(jobSeeker);
        jobSeekerJpaRepository.saveAndFlush(jobSeekerEntity);
    }
}
