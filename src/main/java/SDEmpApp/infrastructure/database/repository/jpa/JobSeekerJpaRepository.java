package SDEmpApp.infrastructure.database.repository.jpa;

import SDEmpApp.infrastructure.database.entities.JobSeekerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerJpaRepository extends JpaRepository<JobSeekerEntity, Integer> {

}
