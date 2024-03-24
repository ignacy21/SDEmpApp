package SDEmpApp.api.controller;

import SDEmpApp.api.dto.JobSeekerDTO;
import SDEmpApp.api.dto.JobSeekerDTOs;
import SDEmpApp.api.dto.finalQueriesDTO.JobSeekerFinalFindQueryDTO;
import SDEmpApp.api.dto.mapper.JobSeekerMapper;
import SDEmpApp.buisness.JobSeekerService;
import SDEmpApp.buisness.LocalizationService;
import SDEmpApp.domain.JobSeeker;
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
@RequestMapping(JobSeekerController.JOB_SEEKER)
@RequiredArgsConstructor
public class JobSeekerController {

    public static final String JOB_SEEKER = "/job-seeker";
    private static final String JOB_SEEKER_RESULT = "/%s";
    private final String CRETE = "/create";
    private final String UPDATE = "/update-data/{jobSeekerId}";
    private final String FIND = "/find";
    private final String FIND_BY_USERNAME = "/find/by-username/{username}";

    private final JobSeekerService jobSeekerService;
    private final LocalizationService localizationService;

    private final JobSeekerMapper jobSeekerMapper;


    @PostMapping(value = CRETE)
    public ResponseEntity<?> createJobSeeker(
            @Valid @RequestBody JobSeekerDTO jobSeekerDTO
    ) {
        Localization localization = localizationService.findLocalization(jobSeekerDTO.getLocalization());
        JobSeeker jobSeeker = jobSeekerMapper.mapFromDTO(jobSeekerDTO);
        JobSeeker createdJobSeeker = jobSeekerService.createJobSeeker(
                jobSeeker.withLocalization(localization)
        );

        return ResponseEntity
                .created(URI.create(JOB_SEEKER + JOB_SEEKER_RESULT.formatted(createdJobSeeker.getJobSeekerId())))
                .build();
    }

    @PutMapping(value = UPDATE)
    public ResponseEntity<?> updateJobSeekerData(
            @PathVariable Integer jobSeekerId,
            @Valid @RequestBody JobSeekerDTO jobSeekerDTO
    ) {
        JobSeeker jobSeekerFind = jobSeekerService.findById(jobSeekerId);
        JobSeeker mappedJobSeekerForUpdate = jobSeekerMapper.mapDTOForUpdate(jobSeekerDTO, jobSeekerFind);
        if (jobSeekerDTO.getLocalization() != null) {
            Localization localization = localizationService.findLocalization(jobSeekerDTO.getLocalization());
            mappedJobSeekerForUpdate.setLocalization(localization);
        }

        JobSeeker jobSeeker = jobSeekerService.updateJobSeekerData(mappedJobSeekerForUpdate);
        log.info("updating jobSeeker: id[%s]".formatted(jobSeeker.getJobSeekerId()));

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = FIND_BY_USERNAME, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobSeekerDTOs findByUsername(
            @PathVariable String username
    ) {
        List<JobSeeker> jobSeekers = jobSeekerService.findByUsername(username);
        List<JobSeekerDTO> jobSeekerDTOs = jobSeekers.stream().map(jobSeekerMapper::mapToDTO).toList();
        return JobSeekerDTOs.of(jobSeekerDTOs);
    }

    @GetMapping(value = FIND, produces = MediaType.APPLICATION_JSON_VALUE)
    public JobSeekerDTOs jobSeekerFinalFind(
            @Valid @RequestBody JobSeekerFinalFindQueryDTO jobSeekerFinalFindQueryDTO
    ) {
        List<JobSeeker> jobSeekers = jobSeekerService.listOfSearchedJobSeekers(jobSeekerFinalFindQueryDTO);
        return JobSeekerDTOs.of(jobSeekers.stream().map(jobSeekerMapper::mapToDTO).toList());
    }
}
