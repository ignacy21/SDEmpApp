package SDEmpApp.buisness;

import SDEmpApp.infrastructure.database.repository.jpa.CandidateJpaRepository;
import SDEmpApp.infrastructure.database.repository.jpa.CompanyJpaRepository;
import SDEmpApp.infrastructure.database.repository.jpa.JobAdvertisementJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PurgeService {

    private CandidateJpaRepository candidateJpaRepository;
    private CompanyJpaRepository companyJpaRepository;
    private JobAdvertisementJpaRepository jobAdvertisementJpaRepository;


    public void purge() {
        jobAdvertisementJpaRepository.deleteAll();
        companyJpaRepository.deleteAll();
        candidateJpaRepository.deleteAll();
    }
}
