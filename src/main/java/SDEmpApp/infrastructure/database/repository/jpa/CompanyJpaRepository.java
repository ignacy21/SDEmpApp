package SDEmpApp.infrastructure.database.repository.jpa;

import SDEmpApp.domain.Company;
import SDEmpApp.infrastructure.database.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, Integer> {

    Optional<CompanyEntity> findByName(String name);

    List<CompanyEntity> findByNameContaining(String name);

    Optional<CompanyEntity> findByEmailAndPassword(String email, String password);
}
