package SDEmpApp.buisness;

import SDEmpApp.api.dto.auxiliary.ExperienceDTO;
import SDEmpApp.api.dto.auxiliary.enums.Experience;
import SDEmpApp.api.dto.auxiliary.enums.Language;
import SDEmpApp.api.dto.auxiliary.enums.Skill;
import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Localization;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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

    public List<JobAdvertisement> findBySpecifiedLanguages(List<Language> languages) {
        List<String> languagesAsStrings = languages.stream().map(Language::name).toList();
        return languagesAsStrings.stream()
                .map(jobAdvertisementDAO::findByLanguage)
                .flatMap(List::stream)
                .distinct()
                .filter(jobAdvert -> new HashSet<>(languagesAsStrings).containsAll(
                        Arrays.stream(jobAdvert.getLanguages().split(";")).toList()
                ))
                .toList();
    }

    public List<JobAdvertisement> findByLocalization(Localization localization) {
        return jobAdvertisementDAO.findByLocalization(localization);
    }

    public JobAdvertisement findById(Integer jobAdvertisementId) {
        return jobAdvertisementDAO.findById(jobAdvertisementId);
    }

    public JobAdvertisement updateJobAdvertisement(JobAdvertisement jobAdvertisement) {
        return jobAdvertisementDAO.updateJobAdvertisement(jobAdvertisement);
    }

    public List<JobAdvertisement> findByExperience(ExperienceDTO experienceDTO) {
        int ordinal = experienceDTO.getExperience().ordinal();
        return Arrays.stream(Experience.values())
                .filter(x -> x.ordinal() <= ordinal)
                .map(Experience::getExperience)
                .map(jobAdvertisementDAO::findByExperience)
                .flatMap(List::stream)
                .distinct()
                .toList();

    }
}
