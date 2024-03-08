package SDEmpApp.buisness;

import SDEmpApp.buisness.DAO.JobSeekerDAO;
import SDEmpApp.domain.JobSeeker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class JobSeekerService {

    private final JobSeekerDAO jobSeekerDAO;

    @Transactional
    public JobSeeker createJobSeeker(JobSeeker jobSeeker) {
        // TODO if jobSeeker's data is already existing
        return jobSeekerDAO.createJobSeeker(jobSeeker);
    }
//
//    private final ReadAndPrepareFileService fileService;
//    private final CandidateDAO candidateDAO;
//
//    public List<Candidate> createCandidates() {
//
//        List<String> inputData = fileService.getData(Keys.MainCommand.CREATE, Keys.SecondCommand.CANDIDATE);
//        List<Candidate> candidates = inputData.stream()
//                .map(line -> line.split(";"))
//                .map(CandidateService::createCandidate)
//                .toList();
//        candidates.forEach(candidateDAO::createCandidate);
//        return candidates;
//    }
//
//    private static Candidate createCandidate(String[] candidateData) {
//        return Candidate.builder()
//                .name(candidateData[0])
//                .surname(candidateData[1])
//                .studentNonStudent(candidateData[2])
//                .phone(candidateData[3])
//                .email(candidateData[4])
//                .linkedin(candidateData[5])
//                .git(candidateData[6])
//                .cv(candidateData[7])
//                .languages(candidateData[8])
//                .skills(candidateData[9])
//                .b2bNormalFit(candidateData[10])
//                .formOfWork(candidateData[11])
//                .experience(candidateData[12])
//                .aboutMe(candidateData[13])
//                .employedUnemployed(candidateData[14])
//                .build();
//    }
}
