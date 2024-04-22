package SDEmpApp.api.controller;

import SDEmpApp.api.dto.CitiesDTO;
import SDEmpApp.buisness.LocalizationService;
import SDEmpApp.domain.Localization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/localization")
@RequiredArgsConstructor
public class LocalizationController {

    private final LocalizationService localizationService;

    private final String FIND_BY_PROVINCE = "/by-province/{provinceName}";

    @GetMapping(value = FIND_BY_PROVINCE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CitiesDTO findByProvinceName(
            @PathVariable String provinceName
    ) {
        List<Localization> localizationByProvince = localizationService.findLocalizationByProvince(provinceName);
        List<String> cities = localizationByProvince.stream()
                .map(Localization::getCityName)
                .toList();

        return CitiesDTO.of(cities);
    }
}
