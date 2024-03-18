package SDEmpApp.buisness;

import SDEmpApp.api.dto.auxiliary.enums.Language;
import SDEmpApp.api.dto.auxiliary.enums.Skill;
import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.domain.JobAdvertisement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        return skills.stream()
                .map(Skill::name)
                .map(jobAdvertisementDAO::findBySkill)
                .flatMap(List::stream)
                .distinct()
                .filter(x -> x.getSkillsNeeded().split(";").length == skills.size())
                .toList();
    }

    public List<JobAdvertisement> findByLanguages(List<Language> languages) {
        List<JobAdvertisement> list = languages.stream()
                .map(Language::name)
                .map(jobAdvertisementDAO::findByLanguage)
                .flatMap(List::stream)
                .distinct()
                .toList();
        return list;
    }
}
