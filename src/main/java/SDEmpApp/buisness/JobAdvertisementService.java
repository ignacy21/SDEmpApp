package SDEmpApp.buisness;

import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.domain.JobAdvertisement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobAdvertisementService {
    private final JobAdvertisementDAO jobAdvertisementDAO;


    //    private final CompanyJpaRepository companyJpaRepository;
//    private final CompanyEntityMapper companyEntityMapper;
//
    public void createJobAdvertisement(JobAdvertisement jobAdvertisement) {
        jobAdvertisementDAO.createJobAdvertisement(jobAdvertisement);
    }
//
//    private JobAdvertisement createJobAdvertisement(String[] strings) {
//        try {
//            CompanyEntity companyEntity = companyJpaRepository.findById(Integer.parseInt(strings[5])).orElseThrow();
//            return JobAdvertisement.builder()
//                    .localization(strings[0])
//                    .languages(strings[1])
//                    .skillsNeeded(List.of(Skill.builder()
//                            .name(strings[2])
//                            .build()))
//                    .duties(strings[3])
//                    .formOfWork(strings[4])
//                    .company(companyEntityMapper
//                            .mapFromEntity(companyEntity))
//                    .build();
//        } catch (NoSuchElementException exception) {
//            throw new RuntimeException("### THERE IS NO SUCH COMPANY (company_id: [" + strings[5] + "])");
//        }
//    }
}
