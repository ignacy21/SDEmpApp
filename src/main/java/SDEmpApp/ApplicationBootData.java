package SDEmpApp;

import SDEmpApp._else.PrepareJobAdvertisementData;
import SDEmpApp.buisness.CompanyService;
import SDEmpApp.buisness.JobAdvertisementService;
import SDEmpApp.buisness.LocalizationService;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.infrastructure.database.repository.jpa.JobAdvertisementJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class ApplicationBootData implements ApplicationRunner {

    private final LocalizationService localizationService;
    private final JobAdvertisementService jobAdvertisementService;
    private final CompanyService companyService;
    private final PrepareJobAdvertisementData prepareJobAdvertisementData;
    private final JobAdvertisementJpaRepository jobAdvertisementJpaRepository;



    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        jobAdvertisementJpaRepository.deleteAll();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int randomLocalizationId = random.nextInt(1, 978);
            JobAdvertisement jobAdvertisement = prepareJobAdvertisementData.generatedJobAdvertisement();
            jobAdvertisement.setLocalization(localizationService.findLocalizationById(randomLocalizationId));
            jobAdvertisement.setCompany(companyService.findCompanyById(1));

            jobAdvertisementService.createJobAdvertisement(jobAdvertisement);
        }

    }
}
