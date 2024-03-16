package SDEmpApp.buisness;

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

    public List<JobAdvertisement> findBySkills(List<Skill> list) {
        return list.stream()
                .map(Enum::name)
                .map(jobAdvertisementDAO::findBySkill)
                .flatMap(List::stream)
                .distinct()
                .toList();
    }
    public List<JobAdvertisement> findByOnlySpecifiedSkills(List<Skill> list) {
        return list.stream()
                .map(Enum::name)
                .map(jobAdvertisementDAO::findBySkill)
                .flatMap(List::stream)
                .distinct()
                .filter(x -> x.getSkillsNeeded().split(";").length == list.size())
                .toList();
    }
}
