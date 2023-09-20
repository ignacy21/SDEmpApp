package SDEmpApp.infrastructure.database.repository.jpa;


import SDEmpApp.infrastructure.database.entities.LocalizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocalizationJpaRepository extends JpaRepository<LocalizationEntity, Integer> {


    Optional<LocalizationEntity> findByProvinceNameAndCityName(String provinceName, String cityName);

    List<LocalizationEntity> findByProvinceName(String provinceName);
}
