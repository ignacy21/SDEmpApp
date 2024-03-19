package SDEmpApp.buisness;

import SDEmpApp.api.dto.auxiliary.enums.Language;
import SDEmpApp.api.dto.auxiliary.enums.Skill;
import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.domain.JobAdvertisement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class JobAdvertisementService {
    private final JobAdvertisementDAO jobAdvertisementDAO;

    public JobAdvertisement createJobAdvertisement(JobAdvertisement jobAdvertisement) {
        return jobAdvertisementDAO.createJobAdvertisement(jobAdvertisement);
    }

    public List<JobAdvertisement> findByFormOfWork(String formOfWork) {
        return jobAdvertisementDAO.findByFormOfWork(formOfWork);
    }

    public List<JobAdvertisement> findBySkills(List<Skill> skills) {
        return skills.stream()
                .map(Skill::name)
                .map(jobAdvertisementDAO::findBySkill)
                .flatMap(List::stream)
                .distinct()
                .toList();
    }

    public List<JobAdvertisement> findByOnlySpecifiedSkills(List<Skill> skills) {
        List<String> listSkillsAsStrings = skills.stream().map(Skill::name).toList();

        return listSkillsAsStrings.stream()
                .map(jobAdvertisementDAO::findBySkill)
                .flatMap(List::stream)
                .distinct()
                .filter(jobAd -> new HashSet<>(listSkillsAsStrings).containsAll(
                        Arrays.stream(jobAd.getSkillsNeeded()
                        .split(";")).toList()))
                .toList();
    }


    public List<JobAdvertisement> findByLanguages(List<Language> languages) {
        return languages.stream()
                .map(Language::name)
                .map(jobAdvertisementDAO::findByLanguage)
                .flatMap(List::stream)
                .distinct()
                .toList();
    }
}
