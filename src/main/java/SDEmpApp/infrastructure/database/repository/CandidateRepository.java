package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.buisness.DAO.CandidateDAO;
import SDEmpApp.domain.Candidate;
import SDEmpApp.infrastructure.database.entities.CandidateEntity;
import SDEmpApp.infrastructure.database.repository.jpa.CandidateJpaRepository;
import SDEmpApp.infrastructure.database.repository.mapper.CandidateEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CandidateRepository implements CandidateDAO {

    private CandidateJpaRepository candidateJpaRepository;
    private CandidateEntityMapper candidateEntityMapper;

    @Override
    public void createCandidate(Candidate candidate) {
        CandidateEntity candidateEntity = candidateEntityMapper.mapToEntity(candidate);
        candidateJpaRepository.saveAndFlush(candidateEntity);
    }
}
