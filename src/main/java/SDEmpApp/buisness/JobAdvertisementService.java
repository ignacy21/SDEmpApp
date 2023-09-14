package SDEmpApp.buisness;

import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.buisness.management.Keys;
import SDEmpApp.buisness.management.ReadAndPrepareFileService;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Skill;
import SDEmpApp.infrastructure.database.entities.CompanyEntity;
import SDEmpApp.infrastructure.database.repository.jpa.CompanyJpaRepository;
import SDEmpApp.infrastructure.database.repository.mapper.CompanyEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class JobAdvertisementService {

    private final ReadAndPrepareFileService fileService;
    private final JobAdvertisementDAO jobAdvertisementDAO;
    private final CompanyJpaRepository companyJpaRepository;
    private final CompanyEntityMapper companyEntityMapper;

    public List<JobAdvertisement> createJobAdvertisements() {
        List<String> inputData = fileService.getData(Keys.MainCommand.CREATE, Keys.SecondCommand.JOB_ADVERTISEMENT);
        List<JobAdvertisement> jobAdvertisements = inputData.stream()
                .map(line -> line.split(";"))
                .map(this::createJobAdvertisement)
                .toList();
        jobAdvertisements.forEach(jobAdvertisementDAO::createJobAdvertisement);
        return jobAdvertisements;
    }

    private JobAdvertisement createJobAdvertisement(String[] strings) {
        try {
            CompanyEntity companyEntity = companyJpaRepository.findById(Integer.parseInt(strings[5])).orElseThrow();
            return JobAdvertisement.builder()
                    .localization(strings[0])
                    .languages(strings[1])
                    .skillsNeeded(List.of(Skill.builder()
                            .name(strings[2])
                            .build()))
                    .duties(strings[3])
                    .formOfWork(strings[4])
                    .company(companyEntityMapper
                            .mapFromEntity(companyEntity))
                    .build();
        } catch (NoSuchElementException exception) {
            throw new RuntimeException("### THERE IS NO SUCH COMPANY (company_id: [" + strings[5] + "])");
        }
    }
}
