package SDEmpApp.api.controller;

import SDEmpApp.api.dto.CompanyDTO;
import SDEmpApp.api.dto.mapper.CompanyMapper;
import SDEmpApp.buisness.CompanyService;
import SDEmpApp.buisness.LocalizationService;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.Localization;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class CompanyController {

    private static final String COMPANY = "/company";

    private final LocalizationService localizationService;
    private final CompanyService companyService;

    private final CompanyMapper companyMapper;


    @GetMapping(value = COMPANY)
    public String homePage(
            @ModelAttribute("companyDTO") CompanyDTO companyDTO,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        model.addAttribute("provinces", prepareProvinces());
        model.addAttribute("cities", findCities());
        return "company_creation";
    }

    private List<String> findCities() {
        return localizationService.findAllCities().stream()
                .map(Localization::getCityName)
                .toList();
    }

    @PostMapping(value = COMPANY)
    public String makeAnAccount(
            @ModelAttribute("companyDTO") CompanyDTO companyDTO,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        CompanyDTO companyDTO1 = companyDTO;
        Company map = companyMapper.map(companyDTO);
        companyService.createCompany(map);

        return "company_create_done";
    }

    private List<String> prepareProvinces() {
        return List.of(
                "Dolnośląskie",
                "Kujawsko-pomorskie",
                "Lubelskie",
                "Lubuskie",
                "Łódzkie",
                "Małopolskie",
                "Mazowieckie",
                "Opolskie",
                "Podkarpackie",
                "Podlaskie",
                "Pomorskie",
                "Śląskie",
                "Świętokrzyskie",
                "Warmińsko-mazurskie",
                "Wielkopolskie",
                "Zachodniopomorskie");
    }

}
