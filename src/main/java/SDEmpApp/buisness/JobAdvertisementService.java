package SDEmpApp.buisness;

import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.buisness.management.Keys;
import SDEmpApp.buisness.management.ReadAndPrepareFileService;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.infrastructure.database.repository.jpa.CompanyJpaRepository;
import SDEmpApp.infrastructure.database.repository.mapper.CompanyEntityMapper;

import java.util.List;

public class JobAdvertisementService {

    private ReadAndPrepareFileService fileService;
    private JobAdvertisementDAO jobAdvertisementDAO;
    private CompanyJpaRepository companyJpaRepository;
    private CompanyEntityMapper companyEntityMapper;

    public List<JobAdvertisement> prepareJobAdvertisementToCreateMap() {
        List<String> inputData = fileService.getData(Keys.MainCommand.CREATE, Keys.SecondCommand.JOB_ADVERTISEMENT);
        List<JobAdvertisement> jobAdvertisements = inputData.stream()
                .map(line -> line.split(";"))
                .map(this::createJobAdvertisement)
                .toList();
        jobAdvertisements.forEach(jobAdvertisement -> jobAdvertisementDAO.createJobAdvertisement(jobAdvertisement));
        return jobAdvertisements;
    }

    private  JobAdvertisement createJobAdvertisement(String[] strings) {
        return JobAdvertisement.builder()
                .localization(strings[0])
                .languages(strings[1])
                .skillsNeeded(strings[2])
                .duties(strings[3])
                .formOfWork(strings[4])
                .company(companyEntityMapper
                        .mapFromEntity(companyJpaRepository.findById(Integer.parseInt(strings[5])).orElseThrow()))
                .build();
    }
}
