package SDEmpApp.api.controller;

import SDEmpApp.api.dto.JobAdvertisementDTO;
import SDEmpApp.api.dto.JobAdvertisementDTOs;
import SDEmpApp.api.dto.finalQueriesDTO.JobAdvertisementFinalFindQueryDTO;
import SDEmpApp.api.dto.mapper.JobAdvertisementMapper;
import SDEmpApp.buisness.CompanyService;
import SDEmpApp.buisness.JobAdvertisementService;
import SDEmpApp.buisness.LocalizationService;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Localization;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(JobAdvertisementController.JOB_ADV)
@RequiredArgsConstructor
public class JobAdvertisementController {

    public static final String JOB_ADV = "/job-advertisement";
    private final String CREATE_JOB_ADVERT = "/create/{companyId}";
    public static final String UPDATE = "/update/{jobAdvertisementId}";
    private static final String JOB_ADVERT_RESULT = "/%s";
    private final String FIND = "/find";
    private final String BY_FORM_OF_WORK = "/form-of-work";
    private final String BY_SKILLS = "/skills";
    private final String BY_SPECIFIED_SKILLS = "/specified-skills";
    private final String BY_LANGUAGES = "/languages";
    private final String BY_SPECIFIED_LANGUAGES = "/specified-languages";
    private final String BY_LOCALIZATION = "/by-localization";
    private final String BY_EXPERIENCE_NEEDED = "/by-experience-needed";
    private final String BY_SALARY = "/by-salary";
    private final String BY_SENIORITY = "/by-seniority";

    private final JobAdvertisementService jobAdvertisementService;
    private final CompanyService companyService;
    private final LocalizationService localizationService;

    private final JobAdvertisementMapper jobAdvertisementMapper;

    @PostMapping(value = CREATE_JOB_ADVERT)
    public ResponseEntity<JobAdvertisementDTO> createJobAdvertisement(
            @PathVariable Integer companyId,
            @Valid @RequestBody JobAdvertisementDTO jobAdvertisementDTO
    ) {
        Localization findLocalizationAdv = localizationService.findLocalization(jobAdvertisementDTO.getLocalization());

        Company findCompany = companyService.findCompanyById(companyId);
        JobAdvertisement jobAdvertisement = jobAdvertisementMapper.mapFromDTO(jobAdvertisementDTO);

        jobAdvertisement.setLocalization(findLocalizationAdv);
        jobAdvertisement.setCompany(findCompany);

        JobAdvertisement jobAdvertCreated = jobAdvertisementService.createJobAdvertisement(jobAdvertisement);
        return ResponseEntity
                .created(URI.create(JOB_ADV + JOB_ADVERT_RESULT.formatted(jobAdvertCreated.getJobAdvertisementId())))
                .build();
    }

    @PutMapping(value = UPDATE)
    public ResponseEntity<?> updateJobAdvertisement(
            @PathVariable Integer jobAdvertisementId,
            @Valid @RequestBody JobAdvertisementDTO jobAdvertisementDTO
    ) {
        JobAdvertisement existingAdvertisement = jobAdvertisementService.findById(jobAdvertisementId);
        if (jobAdvertisementDTO.getLocalization() != null) {
            Localization localization = localizationService.findLocalization(jobAdvertisementDTO.getLocalization());
            existingAdvertisement.setLocalization(localization);
        }
        JobAdvertisement jobAdvertisement = jobAdvertisementMapper.mapJobAdvertisementDTOForUpdate(
                jobAdvertisementDTO,
                existingAdvertisement
        );

        JobAdvertisement updatedJobAdvertisement = jobAdvertisementService.updateJobAdvertisement(jobAdvertisement);
        log.info("updating jobAdvertisement: id[%s]".formatted(updatedJobAdvertisement.getJobAdvertisementId()));
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = FIND, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobAdvertisementDTOs finalJobAdvertisementFindQuery(
            @Valid @RequestBody JobAdvertisementFinalFindQueryDTO jobAdvertisementFinalFindQueryDTO
            ) {
        List<JobAdvertisement> jobAdvertisements = jobAdvertisementService.listOfSearchedJobAdvertisements(
                jobAdvertisementFinalFindQueryDTO
        );
        return JobAdvertisementDTOs.of(jobAdvertisements.stream()
                .map(jobAdvertisementMapper::mapToDTO)
                .toList());
    }
}
