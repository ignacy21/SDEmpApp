package SDEmpApp.buisness;

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

}
