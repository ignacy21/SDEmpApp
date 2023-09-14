package SDEmpApp.infrastructure.database.repository.jpa;

import SDEmpApp.infrastructure.database.entities.CompanyEntity;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@Repository
public interface CompanyJpaRepository extends JpaRepository<CompanyEntity, Integer> {

    Optional<CompanyEntity> findByName(String name);

    List<CompanyEntity> findByJobAdvertisements(List<JobAdvertisementEntity> list);

    List<CompanyEntity> findByNameContaining(String name);

}
