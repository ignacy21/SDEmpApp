package SDEmpApp;

import SDEmpApp._else.PrepareCompanyData;
import SDEmpApp._else.PrepareJobAdvertisementData;
import SDEmpApp.buisness.CompanyService;
import SDEmpApp.buisness.JobAdvertisementService;
import SDEmpApp.buisness.LocalizationService;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.infrastructure.database.repository.jpa.CompanyJpaRepository;
import SDEmpApp.infrastructure.database.repository.jpa.JobAdvertisementJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class ApplicationBootData implements ApplicationRunner {

    private final LocalizationService localizationService;
    private final JobAdvertisementService jobAdvertisementService;
    private final CompanyService companyService;

    private final PrepareJobAdvertisementData prepareJobAdvertisementData;
    private final PrepareCompanyData prepareCompanyData;

    private final JobAdvertisementJpaRepository jobAdvertisementJpaRepository;
    private final CompanyJpaRepository companyJpaRepository;



    @Override
    public void run(ApplicationArguments args) throws Exception {
        int numberOfCompanies = 200;
        int numberOfJobAdvertisements = 800;
        Random random = new Random();
        jobAdvertisementJpaRepository.deleteAll();
        companyJpaRepository.deleteAll();

        for (int i = 0; i < numberOfCompanies; i++) {
            int randomLocalizationId = random.nextInt(1, 978);
            Company company = prepareCompanyData.generateCompany();
            company.setLocalization(localizationService.findLocalizationById(randomLocalizationId));

            companyService.createCompany(company);
        }

        for (int i = 0; i < numberOfJobAdvertisements; i++) {
            int randomLocalizationId = random.nextInt(1, 978);
            JobAdvertisement jobAdvertisement = prepareJobAdvertisementData.generateJobAdvertisement();
            jobAdvertisement.setLocalization(localizationService.findLocalizationById(randomLocalizationId));
            jobAdvertisement.setCompany(companyService.findCompanyByName("company%s".formatted(
                    random.nextInt(0, numberOfCompanies)))
                    .stream()
                    .findFirst()
                    .orElseThrow());

            jobAdvertisementService.createJobAdvertisement(jobAdvertisement);
        }

    }
}
