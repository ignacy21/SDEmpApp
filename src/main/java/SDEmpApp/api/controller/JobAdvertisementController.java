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

@RestController
@RequestMapping(name = JobAdvertisementController.JOB_ADV)
@RequiredArgsConstructor
public class JobAdvertisementController {

    public static final String JOB_ADV = "job-advertisement";

    private final JobAdvertisementMapper jobAdvertisementMapper;
    private final JobAdvertisementService jobAdvertisementService;
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<?> createJobAdvertisement(
            @PathVariable Integer companyId,
            @RequestBody JobAdvertisementDTO jobAdvertisementDTO
    ) {
        Company companyById = companyService.findCompanyById(companyId);
        JobAdvertisement jobAdvertisement = jobAdvertisementMapper.mapFromDTO(jobAdvertisementDTO);
        jobAdvertisement.setCompany(companyById);
        JobAdvertisement jobAdvertisementCreated = jobAdvertisementService.createJobAdvertisement(jobAdvertisement);


        return ResponseEntity.ok().build();
    }
}
