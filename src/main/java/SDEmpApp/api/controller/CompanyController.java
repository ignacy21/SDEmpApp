package SDEmpApp.api.controller;

import SDEmpApp.api.dto.CompaniesDTO;
import SDEmpApp.api.dto.CompanyDTO;
import SDEmpApp.api.dto.mapper.CompanyMapper;
import SDEmpApp.buisness.CompanyService;
import SDEmpApp.buisness.LocalizationService;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.Localization;
import jakarta.persistence.EntityNotFoundException;
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
    private static final String COMPANY_ID = "/{companyId}";
    private static final String COMPANY_NAME = "/{companyName}";

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

    @GetMapping(value = COMPANY + COMPANY_ID)
    public CompanyDTO companyDetails(@PathVariable Integer companyId) {
        return companyService.findById(companyId)
                .map(companyMapper::mapFromDTO)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Company not Found, companyId [%s]".formatted(companyId)
                ));
    }
    @GetMapping(value = COMPANY + COMPANY_NAME)
    public CompaniesDTO companyDetails(@PathVariable String companyName) {
        return CompaniesDTO.of(companyService.findByName(companyName).stream()
                .map(companyMapper::mapFromDTO)
                .toList());
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
        Company map = companyMapper.mapToDTO(companyDTO);
        companyService.createCompany(map);

        return "company_create_done";
    }

    private List<String> findCities() {
        return localizationService.findAllCities().stream()
                .map(Localization::getCityName)
                .toList();
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
