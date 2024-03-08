package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.buisness.DAO.LocalizationDAO;
import SDEmpApp.domain.Localization;
import SDEmpApp.infrastructure.database.repository.jpa.LocalizationJpaRepository;
import SDEmpApp.infrastructure.database.repository.mapper.LocalizationEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class LocalizationRepository implements LocalizationDAO {

    private final LocalizationJpaRepository localizationJpaRepository;

    private final LocalizationEntityMapper localizationEntityMapper;


    @Override
    public Integer findLocalizationForCompany(String provinceName, String cityName) {
        return localizationJpaRepository.findByProvinceNameAndCityName(provinceName, cityName)
                // TODO przydałoby się swój własny wyjątek stworzyć i obsłużyć później w Controllerze
                .orElseThrow(() -> new RuntimeException("There is no such Localization"))
                .getLocalizationId();
    }

    @Override
    public List<Localization> findAllLocalizations() {
        return localizationJpaRepository.findAll().stream()
                .map(localizationEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<Localization> findAvailableCitiesForProvince(String provinceName) {
        return localizationJpaRepository.findByProvinceName(provinceName).stream()
                .map(localizationEntityMapper::mapFromEntity)
                .toList();
    }
}
