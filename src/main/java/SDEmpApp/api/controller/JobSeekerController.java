package SDEmpApp.api.controller;

import SDEmpApp.api.dto.JobAdvertisementDTO;
import SDEmpApp.api.dto.JobAdvertisementDTOs;
import SDEmpApp.api.dto.JobSeekerDTO;
import SDEmpApp.api.dto.JobSeekerDTOs;
import SDEmpApp.api.dto.auxiliary.LanguageDTO;
import SDEmpApp.api.dto.auxiliary.LanguageDTOs;
import SDEmpApp.api.dto.auxiliary.enums.Language;
import SDEmpApp.api.dto.mapper.JobSeekerMapper;
import SDEmpApp.buisness.JobSeekerService;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.JobSeeker;
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
@RequestMapping(JobSeekerController.JOB_SEEKER)
@RequiredArgsConstructor
public class JobSeekerController {

    public static final String JOB_SEEKER = "/job-seeker";
    public static final String JOB_SEEKER_RESULT = "/%s";
    public static final String CRETE = "/create";
    public static final String UPDATE = "/update-data/{jobSeekerId}";
    public static final String FIND = "/find";
    public static final String BY_USERNAME = "/by-username/{username}";
    public static final String IS_STUDENT = "/isStudent/{isStudent}";
    public static final String BY_LANGUAGES = "/languages";
    public static final String BY_SPECIFIED_LANGUAGES = "/specified-languages";

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
        List<JobSeeker> jobSeekers =  jobSeekerService.findByUsername(username);
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
}
