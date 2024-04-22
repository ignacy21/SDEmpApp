package SDEmpApp.buisness.DAO;

import SDEmpApp.domain.Localization;

import java.util.List;


public interface LocalizationDAO {


    List<Localization> findAllLocalizations();

    Localization findLocalizationByProvinceAndCity(String provinceName, String cityName);

    Localization findLocalizationById(Integer localizationId);

    List<Localization> findLocalizationByProvince(String provinceName);
}
