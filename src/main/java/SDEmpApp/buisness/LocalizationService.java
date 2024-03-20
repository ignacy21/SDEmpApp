package SDEmpApp.buisness;

import SDEmpApp.api.dto.LocalizationDTO;
import SDEmpApp.buisness.DAO.LocalizationDAO;
import SDEmpApp.domain.Localization;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocalizationService {

    private final LocalizationDAO localizationDAO;

    public List<Localization> findAllCities() {
        return localizationDAO.findAllLocalizations();
    }
    public Localization findLocalization(LocalizationDTO localizationDTO) {
        return findLocalizationByProvinceAndCity(
                localizationDTO.getProvinceName(),
                localizationDTO.getCityName()
        );
    }

    private Localization findLocalizationByProvinceAndCity(String province, String city) {
        return localizationDAO.findLocalizationByProvinceAndCity(province, city);
    }

    public Localization findLocalizationById(Integer localizationId) {
        return localizationDAO.findLocalizationById(localizationId);
    }

}
