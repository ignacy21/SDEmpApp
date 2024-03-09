package SDEmpApp.api.controller;

import SDEmpApp.api.dto.JobAdvertisementDTO;
import SDEmpApp.api.dto.mapper.JobAdvertisementMapper;
import SDEmpApp.buisness.CompanyService;
import SDEmpApp.buisness.JobAdvertisementService;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(name = JobAdvertisementController.JOB_ADV)
@RequiredArgsConstructor
public class JobAdvertisementController {

    public static final String JOB_ADV = "job-advertisement";
    public static final String CREATE_JOB_ADVERT = "/create/{companyId}";
    public static final String JOB_ADVERT_RESULT = "/{%s}";

    private final JobAdvertisementMapper jobAdvertisementMapper;
    private final JobAdvertisementService jobAdvertisementService;
    private final CompanyService companyService;

    @PostMapping(name = CREATE_JOB_ADVERT)
    public ResponseEntity<?> createJobAdvertisement(
            @PathVariable Integer companyId,
            @RequestBody JobAdvertisementDTO jobAdvertisementDTO
    ) {
        Company companyById = companyService.findCompanyById(companyId);
        JobAdvertisement jobAdvertisement = jobAdvertisementMapper.mapFromDTO(jobAdvertisementDTO);
        jobAdvertisement.setCompany(companyById);
        JobAdvertisement jobAdvertCreated = jobAdvertisementService.createJobAdvertisement(jobAdvertisement);
        return ResponseEntity
                .created(URI.create(JOB_ADV + JOB_ADVERT_RESULT.formatted(jobAdvertCreated.getJobAdvertisementId())))
                .build();
    }
}
