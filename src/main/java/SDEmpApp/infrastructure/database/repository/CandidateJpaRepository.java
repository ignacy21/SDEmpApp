package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.infrastructure.database.entities.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateJpaRepository extends JpaRepository<CandidateEntity, Integer> {
}
