package SDEmpApp.api.controller;

import SDEmpApp.api.controller.data.ControllerData;
import SDEmpApp.api.dto.CompanyDTO;
import SDEmpApp.api.dto.mapper.CompanyMapper;
import SDEmpApp.buisness.CompanyService;
import SDEmpApp.buisness.LocalizationService;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.Localization;
import SDEmpApp.infrastructure.database.entities.CompanyEntity;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(CompanyController.COMPANY)
@AllArgsConstructor
public class CompanyController {

    public static final String COMPANY = "/company";
    public static final String CREATE = "/create";
    public static final String COMPANY_ID_RESULT = "/%s";


    private final LocalizationService localizationService;
    private final CompanyService companyService;

    private final CompanyMapper companyMapper;


    @PostMapping(CREATE)
    public ResponseEntity<CompanyDTO> registerAsCompany(
            @Valid @RequestBody CompanyDTO companyDTO
    ) {
        Company company = Company.builder()
                .name(companyDTO.getName())
                .description(companyDTO.getDescription())
                .localization(Localization.builder()
                        .provinceName(companyDTO.getProvinceName())
                        .cityName(companyDTO.getCityName())
                        .build())
                .email(companyDTO.getEmail())
                .password(companyDTO.getPassword())
                .jobAdvertisements(List.of())
                .build();
        CompanyEntity companyEntity = companyService.createCompany(company);
        return ResponseEntity
                .created(URI.create(COMPANY + COMPANY_ID_RESULT.formatted(companyEntity.getCompanyId())))
                .build();
    }


    @GetMapping(value = COMPANY)
    public String homePage(
            @ModelAttribute("companyDTO") CompanyDTO companyDTO,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        model.addAttribute("provinces", ControllerData.provinces());
        model.addAttribute("cities", localizationService.findCities());
        return "company_creation";
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
        Company map = companyMapper.map(companyDTO);
        companyService.createCompany(map);

        return "company_create_done";
    }

}
