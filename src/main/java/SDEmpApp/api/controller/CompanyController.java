package SDEmpApp.api.controller;

import SDEmpApp.api.dto.CompanyDTO;
import SDEmpApp.api.dto.CompanyDTOs;
import SDEmpApp.api.dto.LocalizationDTO;
import SDEmpApp.api.dto.mapper.CompanyMapper;
import SDEmpApp.buisness.CompanyService;
import SDEmpApp.buisness.LocalizationService;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.Localization;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(CompanyController.COMPANY)
@AllArgsConstructor
public class CompanyController {

    public static final String COMPANY = "/company";
    private final String CREATE = "/create";
    private final String COMPANY_ID_RESULT = "/%s";
    private final String UPDATE_COMPANY_DATA = "/edit-data/{companyId}";
    private final String FIND_BY_NAME = "/find-by-name/{companyName}";
    private final String FIND_BY_LOCALIZATION = "/find-by-localization";

    private final CompanyService companyService;
    private final LocalizationService localizationService;

    private final CompanyMapper companyMapper;

    @PostMapping(value = CREATE)
    public ResponseEntity<CompanyDTO> registerAsCompany(
            @Valid @RequestBody CompanyDTO companyDTO
    ) {
        Localization findLocalization = getLocalization(companyDTO.getLocalization());

        Company company = Company.builder()
                .name(companyDTO.getName())
                .description(companyDTO.getDescription())
                .localization(findLocalization)
                .email(companyDTO.getEmail())
                .password(companyDTO.getPassword())
                .jobAdvertisements(Collections.emptyList())
                .build();
        Company companyFind = companyService.createCompany(company);
        return ResponseEntity
                .created(URI.create(COMPANY + COMPANY_ID_RESULT.formatted(companyFind.getCompanyId())))
                .build();
    }

    @PutMapping(value = UPDATE_COMPANY_DATA)
    public ResponseEntity<CompanyDTO> updateCompanyData(
            @PathVariable Integer companyId,
            @Valid @RequestBody CompanyDTO companyDTO
    ) {
        Company existingCompany = companyService.findCompanyById(companyId);
        Company mappedCompanyForUpdate = companyMapper.mapDTOForUpdate(companyDTO, existingCompany);

        if (companyDTO.getLocalization() != null) {
            Localization findLocalization = getLocalization(companyDTO.getLocalization());
            mappedCompanyForUpdate.setLocalization(findLocalization);
        }

        companyService.updateCompany(existingCompany);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = FIND_BY_NAME, produces = MediaType.APPLICATION_JSON_VALUE)
    public CompanyDTOs findCompanyById(
            @PathVariable String companyName
    ) {
        List<Company> companiesByName = companyService.findCompanyByName(companyName);
        List<CompanyDTO> companiesDTOS = companiesByName.stream()
                .map(companyMapper::mapToDTO)
                .toList();
        return CompanyDTOs.of(companiesDTOS);
    }

    @GetMapping(value = FIND_BY_LOCALIZATION, produces = MediaType.APPLICATION_JSON_VALUE)
    public CompanyDTOs findCompanyByLocalization(
            @Valid @RequestBody LocalizationDTO localizationDTO
    ) {
        Localization findLocalization = getLocalization(localizationDTO);
        List<Company> company = companyService.findByLocalization(findLocalization);

        List<CompanyDTO> companiesDTOS = company.stream().map(companyMapper::mapToDTO).toList();
        return CompanyDTOs.of(companiesDTOS);
    }


    public Localization getLocalization(LocalizationDTO localizationDTO) {
        return localizationService.findLocalizationByProvinceAndCity(
                localizationDTO.getProvinceName(),
                localizationDTO.getCityName()
        );
    }
}
