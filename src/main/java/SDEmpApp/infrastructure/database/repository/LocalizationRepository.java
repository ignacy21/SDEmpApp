package SDEmpApp.infrastructure.database.repository;

import SDEmpApp.buisness.DAO.LocalizationDAO;
import SDEmpApp.domain.Localization;
import SDEmpApp.exceptions.NoSuchLocalizationException;
import SDEmpApp.infrastructure.database.entities.LocalizationEntity;
import SDEmpApp.infrastructure.database.repository.jpa.LocalizationJpaRepository;
import SDEmpApp.infrastructure.database.repository.mapper.LocalizationEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class LocalizationRepository implements LocalizationDAO {

    private final LocalizationJpaRepository localizationJpaRepository;

    private final LocalizationEntityMapper localizationEntityMapper;


    @Override
    public List<Localization> findAllLocalizations() {
        return localizationJpaRepository.findAll().stream()
                .map(localizationEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public List<Localization> findLocalizationByProvince(String provinceName) {
        return localizationJpaRepository.findByProvinceName(provinceName).stream()
                .map(localizationEntityMapper::mapFromEntity)
                .toList();
    }

    @Override
    public Localization findLocalizationByProvinceAndCity(String provinceName, String cityName) {
        Optional<LocalizationEntity> byProvinceNameAndCityName = localizationJpaRepository.findByProvinceNameAndCityName(
                provinceName,
                cityName
        );
        if (byProvinceNameAndCityName.isEmpty()) {
            throw new NoSuchLocalizationException("No localization for: province[%s], city[%s]"
                    .formatted(provinceName, cityName));
        }
        return localizationEntityMapper.mapFromEntity(byProvinceNameAndCityName.get());
    }

    @Override
    public Localization findLocalizationById(Integer localizationId) {
        Optional<LocalizationEntity> findLocalization = localizationJpaRepository.findByLocalizationId(localizationId);
        if (findLocalization.isEmpty()) {
            throw new NoSuchLocalizationException(
                    "No localization for: localization_id[%s]".formatted(localizationId)
            );
        }
        return localizationEntityMapper.mapFromEntity(findLocalization.get());
    }

}
