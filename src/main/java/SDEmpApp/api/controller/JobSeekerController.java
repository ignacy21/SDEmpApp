package SDEmpApp.api.controller;

import SDEmpApp.api.dto.JobSeekerDTO;
import SDEmpApp.api.dto.JobSeekerDTOs;
import SDEmpApp.api.dto.auxiliary.*;
import SDEmpApp.api.dto.auxiliary.enums.EmploymentType;
import SDEmpApp.api.dto.auxiliary.enums.FormOfWork;
import SDEmpApp.api.dto.auxiliary.enums.Language;
import SDEmpApp.api.dto.auxiliary.enums.Skill;
import SDEmpApp.api.dto.mapper.JobSeekerMapper;
import SDEmpApp.buisness.JobSeekerService;
import SDEmpApp.domain.JobSeeker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(JobSeekerController.JOB_SEEKER)
@RequiredArgsConstructor
public class JobSeekerController {

    public static final String JOB_SEEKER = "/job-seeker";
    private final String JOB_SEEKER_RESULT = "/%s";
    private final String CRETE = "/create";
    private final String UPDATE = "/update-data/{jobSeekerId}";
    private final String FIND = "/find";
    private final String BY_USERNAME = "/by-username/{username}";
    private final String IS_STUDENT = "/isStudent/{isStudent}";
    private final String BY_LANGUAGES = "/languages";
    private final String BY_SPECIFIED_LANGUAGES = "/specified-languages";
    private static final String BY_SKILLS = "/skills";
    private final String BY_SPECIFIED_SKILLS = "/specified-skills";
    private final String BY_FORM_OF_EMPLOYMENT = "/form-of-employment";
    private final String BY_FORM_OF_WORK = "/form-of-work";
    private final String BY_EXPERIENCE = "/experience";
    private final String IF_IS_EMPLOYED = "/isEmployed/{isEmployed}";


    private final JobSeekerService jobSeekerService;
    private final JobSeekerMapper jobSeekerMapper;


    @PostMapping(CRETE)
    public ResponseEntity<?> createJobSeeker(
            @Valid @RequestBody JobSeekerDTO jobSeekerDTO
    ) {
        JobSeeker jobSeeker = jobSeekerMapper.mapFromDTO(jobSeekerDTO);
        JobSeeker createdJobSeeker = jobSeekerService.createJobSeeker(jobSeeker);

        return ResponseEntity
                .created(URI.create(JOB_SEEKER + JOB_SEEKER_RESULT.formatted(createdJobSeeker.getJobSeekerId())))
                .build();
    }

    @PutMapping(UPDATE)
    public ResponseEntity<?> updateJobSeekerData(
            @PathVariable Integer jobSeekerId,
            @Valid @RequestBody JobSeekerDTO jobSeekerDTO
    ) {
        JobSeeker jobSeekerFind = jobSeekerService.findById(jobSeekerId);
        jobSeekerFind.setSurname(jobSeekerDTO.getSurname());
        jobSeekerFind.setName(jobSeekerDTO.getName());
        jobSeekerFind.setIsStudent(jobSeekerDTO.getIsStudent());
        jobSeekerFind.setPhone(jobSeekerDTO.getPhone());
        jobSeekerFind.setEmail(jobSeekerDTO.getEmail());
        jobSeekerFind.setLinkedin(jobSeekerDTO.getLinkedin());
        jobSeekerFind.setGit(jobSeekerDTO.getGit());
        jobSeekerFind.setCv(jobSeekerDTO.getCv());
        jobSeekerFind.setLanguages(jobSeekerDTO.getLanguages());
        jobSeekerFind.setSkills(jobSeekerDTO.getSkills());
        jobSeekerFind.setB2bNormalFit(jobSeekerDTO.getB2bNormalFit());
        jobSeekerFind.setFormOfWork(jobSeekerDTO.getFormOfWork());
        jobSeekerFind.setExperience(jobSeekerDTO.getExperience());
        jobSeekerFind.setAboutMe(jobSeekerDTO.getAboutMe());
        jobSeekerFind.setLookingForJob(jobSeekerDTO.getLookingForJob());

        JobSeeker jobSeeker = jobSeekerService.updateJobSeekerData(jobSeekerFind);
        log.info("updating jobSeeker: id[%s]".formatted(jobSeeker.getJobSeekerId()));

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = FIND + BY_USERNAME, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobSeekerDTOs findByUsername(
            @PathVariable String username
    ) {
        List<JobSeeker> jobSeekers = jobSeekerService.findByUsername(username);
        List<JobSeekerDTO> jobSeekerDTOs = jobSeekers.stream().map(jobSeekerMapper::mapToDTO).toList();
        return JobSeekerDTOs.of(jobSeekerDTOs);
    }

    @GetMapping(value = FIND + IS_STUDENT, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobSeekerDTOs findIfIsStudent(
            @PathVariable Boolean isStudent
    ) {
        List<JobSeeker> jobSeekersThatAreStudents = jobSeekerService.findStudents(isStudent);
        List<JobSeekerDTO> jobSeekerDTOs = jobSeekersThatAreStudents.stream().map(jobSeekerMapper::mapToDTO).toList();
        return JobSeekerDTOs.of(jobSeekerDTOs);
    }

    @GetMapping(value = FIND + BY_LANGUAGES, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobSeekerDTOs findByLanguages(
            @Valid @RequestBody LanguageDTOs languageDTOs
    ) {
        List<Language> languages = languageDTOs.getLanguageDTOs().stream().map(LanguageDTO::getLanguage).toList();
        List<JobSeeker> byLanguages = jobSeekerService.findByLanguages(languages);
        List<JobSeekerDTO> list = byLanguages.stream().map(jobSeekerMapper::mapToDTO).toList();
        return JobSeekerDTOs.of(list);
    }

    @GetMapping(value = FIND + BY_SPECIFIED_LANGUAGES, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobSeekerDTOs findBySpecifiedLanguages(
            @Valid @RequestBody LanguageDTOs languageDTOs
    ) {
        List<Language> languages = languageDTOs.getLanguageDTOs().stream().map(LanguageDTO::getLanguage).toList();
        List<JobSeeker> byLanguages = jobSeekerService.findBySpecifiedLanguages(languages);
        List<JobSeekerDTO> list = byLanguages.stream().map(jobSeekerMapper::mapToDTO).toList();
        return JobSeekerDTOs.of(list);
    }


    @GetMapping(value = FIND + BY_SKILLS, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobSeekerDTOs findJobSeekerBySkills(
            @Valid @RequestBody SkillDTOs skillDTOs
    ) {
        List<Skill> skillList = getSkills(skillDTOs);
        List<JobSeeker> bySkills = jobSeekerService.findBySkills(skillList);
        List<JobSeekerDTO> list = bySkills.stream().map(jobSeekerMapper::mapToDTO).toList();
        return JobSeekerDTOs.of(list);
    }

    @GetMapping(value = FIND + BY_SPECIFIED_SKILLS, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobSeekerDTOs findOnlyBySpecifiedSkills(
            @Valid @RequestBody SkillDTOs skillDTOs
    ) {
        List<Skill> skillList = getSkills(skillDTOs);
        List<JobSeeker> bySkills = jobSeekerService.findBySpecifiedSkills(skillList);
        List<JobSeekerDTO> list = bySkills.stream().map(jobSeekerMapper::mapToDTO).toList();
        return JobSeekerDTOs.of(list);
    }

    private static List<Skill> getSkills(SkillDTOs skillDTOs) {
        return skillDTOs.getSkills().stream()
                .map(SkillDTO::getSkill)
                .collect(Collectors.toList());
    }

    @GetMapping(value = FIND + BY_FORM_OF_EMPLOYMENT, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobSeekerDTOs findByFormOfEmployment(
            @Valid @RequestBody EmploymentTypeDTOs employmentTypeDTOs
    ) {
        if (employmentTypeDTOs.getEmploymentTypeDTOs().size() == 1) {
            if (employmentTypeDTOs.getEmploymentTypeDTOs().getFirst().getEmploymentType() == EmploymentType.FIT) {
                List<JobSeekerDTO> allJobSeekers = jobSeekerService.findAll().stream()
                        .map(jobSeekerMapper::mapToDTO).toList();
                return JobSeekerDTOs.of(allJobSeekers);
            }
        }
        List<String> list = new java.util.ArrayList<>(employmentTypeDTOs.getEmploymentTypeDTOs().stream()
                .map(EmploymentTypeDTO::getEmploymentType)
                .map(EmploymentType::name)
                .toList());
        list.add("FIT");
        List<JobSeeker> findJobSeekers = jobSeekerService.findByFormOfEmployment(list);
        List<JobSeekerDTO> jobSeekerDTOs = findJobSeekers.stream().map(jobSeekerMapper::mapToDTO).toList();
        return JobSeekerDTOs.of(jobSeekerDTOs);
    }
    @GetMapping(value = FIND + BY_FORM_OF_WORK, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobSeekerDTOs findByFormOfWork(
            @Valid @RequestBody FormOfWorkDTOs formOfWorkDTOs
    ) {
        if (formOfWorkDTOs.getFormOfWorkDTOs().size() == 1) {
            if (formOfWorkDTOs.getFormOfWorkDTOs().getFirst().getFormOfWork() == FormOfWork.FIT) {
                List<JobSeekerDTO> allJobSeekers = jobSeekerService.findAll().stream()
                        .map(jobSeekerMapper::mapToDTO).toList();
                return JobSeekerDTOs.of(allJobSeekers);
            }
        }
        List<String> list = new java.util.ArrayList<>(formOfWorkDTOs.getFormOfWorkDTOs().stream()
                .map(FormOfWorkDTO::getFormOfWork)
                .map(FormOfWork::name)
                .toList());
        list.add("FIT");
        List<JobSeeker> findJobSeekers = jobSeekerService.findByFormOfWork(list);
        List<JobSeekerDTO> jobSeekerDTOs = findJobSeekers.stream().map(jobSeekerMapper::mapToDTO).toList();
        return JobSeekerDTOs.of(jobSeekerDTOs);
    }

    @GetMapping(value = FIND + BY_EXPERIENCE, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobSeekerDTOs findByExperience(
            @Valid @RequestBody ExperienceDTO experienceDTO
    ) {
        List<JobSeeker> findJobSeekers = jobSeekerService.findByExperience(experienceDTO);
        List<JobSeekerDTO> jobSeekerDTOs = findJobSeekers.stream().map(jobSeekerMapper::mapToDTO).toList();
        return JobSeekerDTOs.of(jobSeekerDTOs);
    }

    @GetMapping(value = FIND + IF_IS_EMPLOYED, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobSeekerDTOs findIfIsEmployed(
            @PathVariable Boolean isEmployed
    ) {
        List<JobSeeker> findJobSeekers = jobSeekerService.findIfIsEmployed(isEmployed);
        List<JobSeekerDTO> jobSeekerDTOs = findJobSeekers.stream().map(jobSeekerMapper::mapToDTO).toList();
        return JobSeekerDTOs.of(jobSeekerDTOs);
    }
}
