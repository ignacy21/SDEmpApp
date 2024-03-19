package SDEmpApp.buisness;

import SDEmpApp.api.dto.auxiliary.enums.Language;
import SDEmpApp.buisness.DAO.JobSeekerDAO;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.JobSeeker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class JobSeekerService {

    private final JobSeekerDAO jobSeekerDAO;

    @Transactional
    public JobSeeker createJobSeeker(JobSeeker jobSeeker) {
        // TODO if jobSeeker's data is already existing
        return jobSeekerDAO.createJobSeeker(jobSeeker);
    }

    public JobSeeker updateJobSeekerData(JobSeeker jobSeeker) {
        return jobSeekerDAO.updateJobSeekerData(jobSeeker);
    }

    public JobSeeker findById(Integer jobSeekerId) {
        return jobSeekerDAO.findById(jobSeekerId);
    }

    public List<JobSeeker> findByUsername(String username) {
        return Arrays.stream(username.split(" "))
                .map(jobSeekerDAO::findJobSeekerByUsername)
                .flatMap(List::stream)
                .distinct()
                .toList();


    }

    public List<JobSeeker> findStudents(Boolean isStudent) {
        return jobSeekerDAO.findStudents(isStudent);
    }

    public List<JobSeeker> findByLanguages(List<Language> languages) {
        return languages.stream()
                .map(Language::name)
                .map(jobSeekerDAO::findByLanguage)
                .flatMap(List::stream)
                .distinct()
                .toList();
    }

    public List<JobSeeker> findBySpecifiedLanguages(List<Language> languages) {
        List<String> languagesAsStrings = languages.stream().map(Language::name).toList();
        return languagesAsStrings.stream()
                .map(jobSeekerDAO::findByLanguage)
                .flatMap(List::stream)
                .distinct()
                .filter(jobSeeker -> new HashSet<>(Arrays.stream(jobSeeker.getLanguages().split(";")).toList()).containsAll(
                        languagesAsStrings
                ))
                .toList();
    }
}
