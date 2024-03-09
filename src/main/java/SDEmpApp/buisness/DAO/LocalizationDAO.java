package SDEmpApp.buisness.DAO;

import SDEmpApp.domain.Localization;

import java.util.List;


public interface LocalizationDAO {

    Integer findLocalizationForCompany(String provinceName, String cityName);

    List<Localization> findAllLocalizations();

    List<Localization> findAvailableCitiesForProvince(String provinceName);

    Localization findLocalizationByProvinceAndCity(String provinceName, String cityName);
}
