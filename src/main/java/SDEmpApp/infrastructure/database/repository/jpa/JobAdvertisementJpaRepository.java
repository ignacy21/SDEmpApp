package SDEmpApp.infrastructure.database.repository.jpa;

import SDEmpApp.domain.Localization;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobAdvertisementJpaRepository extends JpaRepository<JobAdvertisementEntity, Integer> {

    List<JobAdvertisementEntity> findByLocalizationId(Integer localizationId);
    Optional<JobAdvertisementEntity> findByFormOfWork(String name);


}

