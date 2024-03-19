package SDEmpApp.buisness;

import SDEmpApp.api.dto.auxiliary.ExperienceDTO;
import SDEmpApp.api.dto.auxiliary.enums.Experience;
import SDEmpApp.api.dto.auxiliary.enums.Language;
import SDEmpApp.api.dto.auxiliary.enums.Skill;
import SDEmpApp.buisness.DAO.JobSeekerDAO;
import SDEmpApp.domain.JobSeeker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class JobSeekerService {

    private final JobSeekerDAO jobSeekerDAO;

    @Transactional
    public JobSeeker createJobSeeker(JobSeeker jobSeeker) {
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

    public List<JobSeeker> findBySkills(List<Skill> skillList) {
        return skillList.stream()
                .map(Skill::name)
                .map(jobSeekerDAO::findBySkill)
                .flatMap(List::stream)
                .distinct()
                .toList();

    }

    public List<JobSeeker> findBySpecifiedSkills(List<Skill> skillList) {
        List<String> skillsAsStrings = skillList.stream().map(Skill::name).toList();
        return skillsAsStrings.stream()
                .map(jobSeekerDAO::findBySkill)
                .flatMap(List::stream)
                .distinct()
                .filter(jobAdvert -> new HashSet<>(skillsAsStrings).containsAll(
                        Arrays.stream(jobAdvert.getSkills()
                                .split(";")).toList()
                ))
                .toList();

    }

    public List<JobSeeker> findAll() {
        return jobSeekerDAO.findAll();
    }

    public List<JobSeeker> findByFormOfEmployment(List<String> formsOfEmployment) {
        return formsOfEmployment.stream()
                .map(jobSeekerDAO::findByFormOfEmployment)
                .flatMap(List::stream)
                .distinct()
                .toList();

    }

    public List<JobSeeker> findByFormOfWork(List<String> requiredFormsOfWork) {
        return requiredFormsOfWork.stream()
                .map(jobSeekerDAO::findByFormOfWork)
                .flatMap(List::stream)
                .distinct()
                .toList();
    }

    public List<JobSeeker> findByExperience(ExperienceDTO experienceDTO) {
        int ordinal = experienceDTO.getExperience().ordinal();
        List<List<JobSeeker>> listOfJobSeekerList = new ArrayList<>();
        Experience[] values = Experience.values();
        for (Experience value : values) {
            if (ordinal <= value.ordinal()) {
                List<JobSeeker> byExperience = jobSeekerDAO.findByExperience(value.getExperience());
                listOfJobSeekerList.add(byExperience);
            }
        }

        return listOfJobSeekerList.stream()
                .flatMap(List::stream)
                .toList();
    }
}
