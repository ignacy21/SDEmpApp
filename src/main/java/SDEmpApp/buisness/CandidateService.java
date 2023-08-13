package SDEmpApp.buisness;

import SDEmpApp.buisness.management.Keys;
import SDEmpApp.buisness.management.ReadAndPrepareFileService;
import SDEmpApp.domain.Candidate;

import java.util.List;

public class CandidateService {

    private ReadAndPrepareFileService fileService;

    public List<Candidate> prepareCandidatesToCreateMap() {

        List<String> inputData = fileService.getData(Keys.MainCommand.CREATE, Keys.SecondCommand.CANDIDATE);
        return inputData.stream()
                .map(line -> line.split(";"))
                .map(CandidateService::createCandidate)
                .toList();
    }

    private static Candidate createCandidate(String[] candidateData) {
        return Candidate.builder()
                .name(candidateData[0])
                .surname(candidateData[1])
                .studentNonStudent(candidateData[2])
                .phone(candidateData[3])
                .email(candidateData[4])
                .linkedin(candidateData[5])
                .git(candidateData[6])
                .cv(candidateData[7])
                .languages(candidateData[8])
                .skills(candidateData[9])
                .b2bNormalFit(candidateData[10])
                .formOfWork(candidateData[11])
                .experience(candidateData[12])
                .aboutMe(candidateData[13])
                .employedUnemployed(candidateData[14])
                .build();
    }
}
