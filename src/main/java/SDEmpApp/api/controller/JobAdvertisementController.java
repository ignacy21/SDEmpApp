package SDEmpApp.api.controller;

import SDEmpApp.api.dto.JobAdvertisementDTO;
import SDEmpApp.api.dto.JobAdvertisementDTOs;
import SDEmpApp.api.dto.LocalizationDTO;
import SDEmpApp.api.dto.auxiliary.*;
import SDEmpApp.api.dto.auxiliary.enums.Language;
import SDEmpApp.api.dto.auxiliary.enums.Skill;
import SDEmpApp.api.dto.mapper.JobAdvertisementMapper;
import SDEmpApp.buisness.CompanyService;
import SDEmpApp.buisness.JobAdvertisementService;
import SDEmpApp.buisness.LocalizationService;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Localization;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

    private final JobAdvertisementService jobAdvertisementService;
    private final CompanyService companyService;
    private final LocalizationService localizationService;

    private final CompanyController companyController;

    private final JobAdvertisementMapper jobAdvertisementMapper;

    @PostMapping(value = CREATE_JOB_ADVERT)
    public ResponseEntity<JobAdvertisementDTO> createJobAdvertisement(
            @PathVariable Integer companyId,
            @Valid @RequestBody JobAdvertisementDTO jobAdvertisementDTO
    ) {
        LocalizationDTO localizationDTO = jobAdvertisementDTO.getLocalization();
        Localization findLocalizationAdv = localizationService.findLocalizationByProvinceAndCity(
                localizationDTO.getProvinceName(),
                localizationDTO.getCityName()
        );

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
            Localization localization = companyController.getLocalization(jobAdvertisementDTO.getLocalization());
            existingAdvertisement.setLocalization(localization);
        }
        JobAdvertisement jobAdvertisement = jobAdvertisementMapper.mapJobAdvertisementDTOForUpdate(
                jobAdvertisementDTO,
                existingAdvertisement
        );

        JobAdvertisement jobAdvertisement1 = jobAdvertisementService.updateJobAdvertisement(jobAdvertisement);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = FIND + BY_FORM_OF_WORK, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobAdvertisementDTOs findByFormOfWork(
            @Valid @RequestBody FormOfWorkDTO formOfWorkDTO
    ) {
        String formOfWork = formOfWorkDTO.getFormOfWork().name();
        List<JobAdvertisement> byFormOfWork = jobAdvertisementService.findByFormOfWork(formOfWork);
        List<JobAdvertisementDTO> list = byFormOfWork.stream().map(jobAdvertisementMapper::mapToDTO).toList();
        return JobAdvertisementDTOs.of(list);
    }

    @GetMapping(value = FIND + BY_SKILLS, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobAdvertisementDTOs findBySkills(
            @Valid @RequestBody SkillDTOs skillDTOs
    ) {
        List<Skill> skillList = skillDTOs.getSkills().stream()
                .map(SkillDTO::getSkill)
                .collect(Collectors.toList());
        List<JobAdvertisement> bySkills = jobAdvertisementService.findBySkills(skillList);
        List<JobAdvertisementDTO> list = bySkills.stream().map(jobAdvertisementMapper::mapToDTO).toList();
        return JobAdvertisementDTOs.of(list);
    }

    @GetMapping(value = FIND + BY_SPECIFIED_SKILLS, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobAdvertisementDTOs findOnlyBySpecifiedSkills(
            @Valid @RequestBody SkillDTOs skillDTOs
    ) {
        List<Skill> skillList = skillDTOs.getSkills().stream()
                .map(SkillDTO::getSkill)
                .collect(Collectors.toList());
        List<JobAdvertisement> bySpecifiedSkills = jobAdvertisementService.findByOnlySpecifiedSkills(skillList);
        List<JobAdvertisementDTO> list = bySpecifiedSkills.stream().map(jobAdvertisementMapper::mapToDTO).toList();
        return JobAdvertisementDTOs.of(list);
    }

    @GetMapping(value = FIND + BY_LANGUAGES, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobAdvertisementDTOs findByLanguages(
            @Valid @RequestBody LanguageDTOs languageDTOs
    ) {
        List<Language> languages = languageDTOs.getLanguageDTOs().stream().map(LanguageDTO::getLanguage).toList();
        List<JobAdvertisement> byLanguages = jobAdvertisementService.findByLanguages(languages);
        List<JobAdvertisementDTO> list = byLanguages.stream().map(jobAdvertisementMapper::mapToDTO).toList();
        return JobAdvertisementDTOs.of(list);
    }
    @GetMapping(value = FIND + BY_SPECIFIED_LANGUAGES, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobAdvertisementDTOs findBySpecifiedLanguages(
            @Valid @RequestBody LanguageDTOs languageDTOs
    ) {
        List<Language> languages = languageDTOs.getLanguageDTOs().stream().map(LanguageDTO::getLanguage).toList();
        List<JobAdvertisement> byLanguages = jobAdvertisementService.findBySpecifiedLanguages(languages);
        List<JobAdvertisementDTO> list = byLanguages.stream().map(jobAdvertisementMapper::mapToDTO).toList();
        return JobAdvertisementDTOs.of(list);
    }
    @GetMapping(value = FIND + BY_LOCALIZATION, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobAdvertisementDTOs findJobAdvertisementByLocalization(
            @Valid @RequestBody LocalizationDTO localizationDTO
    ) {
        Localization findLocalization = companyController.getLocalization(localizationDTO);
        List<JobAdvertisement> jobAdvertisements = jobAdvertisementService.findByLocalization(findLocalization);

        List<JobAdvertisementDTO> companiesDTOS = jobAdvertisements.stream()
                .map(jobAdvertisementMapper::mapToDTO)
                .toList();
        return JobAdvertisementDTOs.of(companiesDTOS);
    }
}
