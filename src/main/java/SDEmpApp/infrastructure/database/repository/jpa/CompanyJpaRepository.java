package SDEmpApp.infrastructure.database.repository.jpa;

import SDEmpApp.infrastructure.database.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, Integer> {

    List<CompanyEntity> findByNameContaining(String name);

    Optional<CompanyEntity> findByEmailAndPassword(String email, String password);

    Optional<CompanyEntity> findByCompanyId(int id);
}
