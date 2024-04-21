package SDEmpApp;

import SDEmpApp.JWT.user.UserRepository;
import SDEmpApp._else.PrepareCompanyData;
import SDEmpApp._else.PrepareJobAdvertisementData;
import SDEmpApp._else.PrepareJobSeekerData;
import SDEmpApp.buisness.CompanyService;
import SDEmpApp.buisness.JobAdvertisementService;
import SDEmpApp.buisness.JobSeekerService;
import SDEmpApp.buisness.LocalizationService;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.JobSeeker;
import SDEmpApp.infrastructure.database.repository.jpa.CompanyJpaRepository;
import SDEmpApp.infrastructure.database.repository.jpa.JobAdvertisementJpaRepository;
import SDEmpApp.infrastructure.database.repository.jpa.JobSeekerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class ApplicationBootData implements ApplicationRunner {

    private final JobSeekerService jobSeekerService;
    private final CompanyService companyService;
    private final JobAdvertisementService jobAdvertisementService;
    private final LocalizationService localizationService;

    private final PrepareJobAdvertisementData prepareJobAdvertisementData;
    private final PrepareJobSeekerData prepareJobSeekerData;
    private final PrepareCompanyData prepareCompanyData;

    private final JobAdvertisementJpaRepository jobAdvertisementJpaRepository;
    private final CompanyJpaRepository companyJpaRepository;
    private final JobSeekerJpaRepository jobSeekerJpaRepository;
    private final UserRepository userRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        int numberOfJobSeekers = 1000;
        int numberOfCompanies = 200;
        int numberOfJobAdvertisements = 800;
        Random random = new Random();

        jobSeekerJpaRepository.deleteAll();
        jobAdvertisementJpaRepository.deleteAll();
        companyJpaRepository.deleteAll();
        userRepository.deleteAll();


        for (int i = 0; i < numberOfJobSeekers; i++) {
            int randomLocalizationId = random.nextInt(400, 500);
            JobSeeker jobSeeker = prepareJobSeekerData.generateJobSeeker();
            jobSeeker.setLocalization(localizationService.findLocalizationById(randomLocalizationId));

            jobSeekerService.createJobSeeker(jobSeeker);
        }

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
