package SDEmpApp.api.controller;

import SDEmpApp.api.dto.CompanyDTO;
import SDEmpApp.buisness.CompanyService;
import SDEmpApp.buisness.LocalizationService;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.Localization;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public static final String UPDATE_COMPANY_DATA = "/edit-data/{companyId}";

    private final CompanyService companyService;
    private final LocalizationService localizationService;

    @PostMapping(CREATE)
    public ResponseEntity<CompanyDTO> registerAsCompany(
            @Valid @RequestBody CompanyDTO companyDTO
    ) {
        Localization findLocalization = localizationService.findLocalizationByProvinceAndCity(
                companyDTO.getProvinceName(),
                companyDTO.getCityName()
        );

        Company company = Company.builder()
                .name(companyDTO.getName())
                .description(companyDTO.getDescription())
                .localization(findLocalization)
                .email(companyDTO.getEmail())
                .password(companyDTO.getPassword())
                .jobAdvertisements(List.of())
                .build();
        Company companyFind = companyService.createCompany(company);
        return ResponseEntity
                .created(URI.create(COMPANY + COMPANY_ID_RESULT.formatted(companyFind.getCompanyId())))
                .build();
    }

    @PutMapping(UPDATE_COMPANY_DATA)
    public ResponseEntity<CompanyDTO> updateCompanyData(
            @PathVariable Integer companyId,
            @Valid @RequestBody CompanyDTO companyDTO
    ) {
        Company existingCompany = companyService.findCompanyById(companyId);
        existingCompany.setName(companyDTO.getName());
        existingCompany.setDescription(companyDTO.getDescription());
        existingCompany.setLocalization(Localization.builder()
                .provinceName(companyDTO.getProvinceName())
                .cityName(companyDTO.getCityName())
                .build());
        companyService.updateCompany(existingCompany);
        return ResponseEntity.ok().build();
    }

}
