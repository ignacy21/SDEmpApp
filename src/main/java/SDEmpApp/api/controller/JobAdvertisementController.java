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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        String sortedLanguages = jobAdvertisementService.sortCsv(jobAdvertisementDTO.getLanguages());
        String sortedSkills = jobAdvertisementService.sortCsv(jobAdvertisementDTO.getSkillsNeeded());

        Company findCompany = companyService.findCompanyById(companyId);
        JobAdvertisement jobAdvertisement = jobAdvertisementMapper.mapFromDTO(jobAdvertisementDTO
                .withLanguages(sortedLanguages)
                .withSkillsNeeded(sortedSkills)
        );

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
