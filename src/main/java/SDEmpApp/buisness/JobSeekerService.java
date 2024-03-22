package SDEmpApp.buisness;

import SDEmpApp.api.dto.LocalizationDTO;
import SDEmpApp.api.dto.auxiliary.*;
import SDEmpApp.api.dto.auxiliary.enums.*;
import SDEmpApp.api.dto.finalQueriesDTO.JobSeekerFinalFindQueryDTO;
import SDEmpApp.buisness.DAO.JobSeekerDAO;
import SDEmpApp.domain.JobSeeker;
import SDEmpApp.domain.Localization;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobSeekerService {

    private final JobSeekerDAO jobSeekerDAO;

    private final LocalizationService localizationService;

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

    public List<JobSeeker> findAll() {
        return jobSeekerDAO.findAll();
    }

    public List<JobSeeker> findByUsername(String username) {
        return Arrays.stream(username.split(" "))
                .map(jobSeekerDAO::findJobSeekerByUsername)
                .flatMap(List::stream)
                .distinct()
                .toList();
    }

    public List<JobSeeker> findIfIsStudent(Boolean isStudent) {
        List<JobSeeker> jobSeekersThatAreStudents = jobSeekerDAO.findStudents(isStudent);
        return jobSeekersThatAreStudents.stream().toList();
    }

    public List<JobSeeker> findByLanguages(LanguageDTOs languageDTOs) {
        List<Language> languages = languageDTOs.getLanguageDTOs().stream().map(LanguageDTO::getLanguage).toList();
        List<JobSeeker> byLanguages = languages.stream()
                .map(Language::name)
                .map(jobSeekerDAO::findByLanguage)
                .flatMap(List::stream)
                .distinct()
                .toList();
        return byLanguages.stream().toList();
    }

    public List<JobSeeker> findBySpecifiedLanguages(LanguageDTOs languageDTOs) {
        List<Language> languages = languageDTOs.getLanguageDTOs().stream().map(LanguageDTO::getLanguage).toList();
        List<String> languagesAsStrings = languages.stream().map(Language::name).toList();

        List<JobSeeker> findJobSeekers = languagesAsStrings.stream()
                .map(jobSeekerDAO::findByLanguage)
                .flatMap(List::stream)
                .distinct()
                .filter(jobSeeker -> new HashSet<>(Arrays.stream(jobSeeker.getLanguages().split(";")).toList()).containsAll(
                        languagesAsStrings
                ))
                .toList();
        return findJobSeekers.stream().toList();
    }


    public List<JobSeeker> findJobSeekerBySkills(SkillDTOs skillDTOs) {
        List<Skill> skillList = getSkills(skillDTOs);
        List<JobSeeker> bySkills = skillList.stream()
                .map(Skill::name)
                .map(jobSeekerDAO::findBySkill)
                .flatMap(List::stream)
                .distinct()
                .toList();
        return bySkills.stream().toList();
    }

    public List<JobSeeker> findBySpecifiedSkills(SkillDTOs skillDTOs) {
        List<Skill> skillList = getSkills(skillDTOs);
        List<String> skillsAsStrings = skillList.stream().map(Skill::name).toList();
        List<JobSeeker> findJobSeekers = skillsAsStrings.stream()
                .map(jobSeekerDAO::findBySkill)
                .flatMap(List::stream)
                .distinct()
                .filter(jobAdvert -> new HashSet<>(skillsAsStrings).containsAll(
                        Arrays.stream(jobAdvert.getSkills()
                                .split(";")).toList()
                ))
                .toList();
        return findJobSeekers.stream().toList();
    }

    public List<JobSeeker> findByFormOfEmployment(EmploymentTypeDTOs employmentTypeDTOs) {
        if (employmentTypeDTOs.getEmploymentTypeDTOs().size() == 1) {
            if (employmentTypeDTOs.getEmploymentTypeDTOs().getFirst().getEmploymentType() == EmploymentType.FIT) {
                return findAll().stream().toList();

            }
        }
        List<String> formsOfEmployment = new java.util.ArrayList<>(employmentTypeDTOs.getEmploymentTypeDTOs().stream()
                .map(EmploymentTypeDTO::getEmploymentType)
                .map(EmploymentType::name)
                .toList());
        formsOfEmployment.add("FIT");
        List<JobSeeker> findJobSeekers = formsOfEmployment.stream()
                .map(jobSeekerDAO::findByFormOfEmployment)
                .flatMap(List::stream)
                .distinct()
                .toList();
        return findJobSeekers.stream().toList();
    }

    public List<JobSeeker> findJobSeekersByFormOfWork(FormOfWorkDTOs formOfWorkDTOs) {
        if (formOfWorkDTOs.getFormOfWorkDTOs().size() == 1) {
            if (formOfWorkDTOs.getFormOfWorkDTOs().getFirst().getFormOfWork() == FormOfWork.FIT) {
                return findAll().stream().toList();

            }
        }
        List<String> requiredFormsOfWorkAsString = new java.util.ArrayList<>(formOfWorkDTOs.getFormOfWorkDTOs().stream()
                .map(FormOfWorkDTO::getFormOfWork)
                .map(FormOfWork::name)
                .toList());
        requiredFormsOfWorkAsString.add("FIT");

        List<JobSeeker> findJobSeekers = requiredFormsOfWorkAsString.stream()
                .map(jobSeekerDAO::findByFormOfWork)
                .flatMap(List::stream)
                .distinct()
                .toList();
        return findJobSeekers.stream().toList();
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
        List<JobSeeker> jobSeekers = listOfJobSeekerList.stream()
                .flatMap(List::stream)
                .toList();
        return jobSeekers.stream().toList();
    }

    public List<JobSeeker> findIfIsEmployed(Boolean isEmployed) {
        List<JobSeeker> findJobSeekers = jobSeekerDAO.findIfIsEmployed(isEmployed);
        return findJobSeekers.stream().toList();
    }

    public List<JobSeeker> findIfIsLookingForJob(Boolean isLookingForJob) {
        List<JobSeeker> findJobSeekers = jobSeekerDAO.findIfIsLookingForJob(isLookingForJob);
        return findJobSeekers.stream().toList();
    }

    public List<JobSeeker> findJobSeekersByLocalization(LocalizationDTO localizationDTO) {
        Localization localization = localizationService.findLocalization(localizationDTO);
        List<JobSeeker> jobSeekers = jobSeekerDAO.findByLocalization(localization);
        return jobSeekers.stream().toList();
    }

    public List<JobSeeker> checkWhatIsNullAndReturnListOfSearchedJobSeekers(JobSeekerFinalFindQueryDTO jobSeekerFinalFindQueryDTO) {
        List<JobSeeker> jobSeekerDTOs = new ArrayList<>();
        if (jobSeekerFinalFindQueryDTO.getLocalization() != null) {
            jobSeekerDTOs.addAll(findJobSeekersByLocalization(jobSeekerFinalFindQueryDTO.getLocalization()));
        }
        if (jobSeekerFinalFindQueryDTO.getIsLookingForJob() != null) {
            jobSeekerDTOs.addAll(findIfIsLookingForJob(jobSeekerFinalFindQueryDTO.getIsLookingForJob()));
        }
        if (jobSeekerFinalFindQueryDTO.getIsEmployed() != null) {
            jobSeekerDTOs.addAll(findIfIsEmployed(jobSeekerFinalFindQueryDTO.getIsEmployed()));
        }
        if (jobSeekerFinalFindQueryDTO.getExperience() != null) {
            jobSeekerDTOs.addAll(findByExperience(jobSeekerFinalFindQueryDTO.getExperience()));
        }
        if (jobSeekerFinalFindQueryDTO.getFormsOfWork() != null) {
            jobSeekerDTOs.addAll(findJobSeekersByFormOfWork(jobSeekerFinalFindQueryDTO.getFormsOfWork()));
        }
        if (jobSeekerFinalFindQueryDTO.getFormsOfEmployment() != null) {
            jobSeekerDTOs.addAll(findByFormOfEmployment(jobSeekerFinalFindQueryDTO.getFormsOfEmployment()));
        }

        if (jobSeekerFinalFindQueryDTO.getIfSpecifiedSkills()) {
            if (jobSeekerFinalFindQueryDTO.getSkills() != null) {
                jobSeekerDTOs.addAll(findBySpecifiedSkills(jobSeekerFinalFindQueryDTO.getSkills()));
            }
        } else {
            if (jobSeekerFinalFindQueryDTO.getSkills() != null) {
                jobSeekerDTOs.addAll(findJobSeekerBySkills(jobSeekerFinalFindQueryDTO.getSkills()));
            }
        }

        if (jobSeekerFinalFindQueryDTO.getIsSpecifiedLanguage()) {
            if (jobSeekerFinalFindQueryDTO.getLanguageDTOs() != null) {
                jobSeekerDTOs.addAll(findBySpecifiedLanguages(jobSeekerFinalFindQueryDTO.getLanguageDTOs()));
            }
        } else {
            if (jobSeekerFinalFindQueryDTO.getLanguageDTOs() != null) {
                jobSeekerDTOs.addAll(findByLanguages(jobSeekerFinalFindQueryDTO.getLanguageDTOs()));
            }
        }
        if (jobSeekerFinalFindQueryDTO.getIsStudent() != null) {
            jobSeekerDTOs.addAll(findIfIsStudent(jobSeekerFinalFindQueryDTO.getIsStudent()));
        }
        return jobSeekerDTOs.stream()
                .distinct()
                .toList();
    }


    private static List<Skill> getSkills(SkillDTOs skillDTOs) {
        return skillDTOs.getSkills().stream()
                .map(SkillDTO::getSkill)
                .collect(Collectors.toList());
    }
}
