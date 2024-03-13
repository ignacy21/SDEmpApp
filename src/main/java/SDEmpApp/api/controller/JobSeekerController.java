package SDEmpApp.api.controller;

import SDEmpApp.api.dto.JobSeekerDTO;
import SDEmpApp.api.dto.mapper.JobSeekerMapper;
import SDEmpApp.buisness.JobSeekerService;
import SDEmpApp.domain.JobSeeker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping(JobSeekerController.JOB_SEEKER)
@RequiredArgsConstructor
public class JobSeekerController {

    public static final String JOB_SEEKER = "/job-seeker";
    public static final String JOB_SEEKER_RESULT = "/%s";
    public static final String CRETE = "/create";
    public static final String UPDATE = "/update-data/{jobSeekerId}";

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
}
