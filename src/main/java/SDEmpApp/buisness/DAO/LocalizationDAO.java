package SDEmpApp.buisness.DAO;

import SDEmpApp.domain.Localization;

import java.util.List;
import java.util.Optional;


public interface LocalizationDAO {


    List<Localization> findAllLocalizations();

    List<Localization> findAvailableCitiesForProvince(String provinceName);

    Localization findLocalizationByProvinceAndCity(String provinceName, String cityName);

    Localization findLocalizationById(Integer localizationId);
}
