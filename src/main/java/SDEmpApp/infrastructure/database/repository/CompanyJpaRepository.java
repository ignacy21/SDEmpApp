package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.infrastructure.database.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, Integer> {
}
