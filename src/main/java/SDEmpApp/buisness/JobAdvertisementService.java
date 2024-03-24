package SDEmpApp.buisness;

import SDEmpApp.api.dto.LocalizationDTO;
import SDEmpApp.api.dto.auxiliary.*;
import SDEmpApp.api.dto.auxiliary.enums.Experience;
import SDEmpApp.api.dto.auxiliary.enums.Language;
import SDEmpApp.api.dto.auxiliary.enums.Skill;
import SDEmpApp.api.dto.finalQueriesDTO.JobAdvertisementFinalFindQueryDTO;
import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Localization;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class JobAdvertisementService {
    private final JobAdvertisementDAO jobAdvertisementDAO;
    private final LocalizationService localizationService;

    public JobAdvertisement createJobAdvertisement(JobAdvertisement jobAdvertisement) {
        return jobAdvertisementDAO.createJobAdvertisement(jobAdvertisement);
    }

    public JobAdvertisement findById(Integer jobAdvertisementId) {
        return jobAdvertisementDAO.findById(jobAdvertisementId);
    }

    public JobAdvertisement updateJobAdvertisement(JobAdvertisement jobAdvertisement) {
        return jobAdvertisementDAO.updateJobAdvertisement(jobAdvertisement);
    }

    public List<JobAdvertisement> findByFormOfWork(FormOfWorkDTO formOfWorkDTO) {
        String formOfWork = formOfWorkDTO.getFormOfWork().name();
        return jobAdvertisementDAO.findByFormOfWork(formOfWork);
    }

    public List<JobAdvertisement> findBySkills(SkillDTOs skillDTOs) {
        return skillDTOs.getSkills().stream()
                .map(SkillDTO::getSkill)
                .map(Skill::name)
                .map(jobAdvertisementDAO::findBySkill)
                .flatMap(List::stream)
                .distinct()
                .toList();
    }

    public List<JobAdvertisement> findOnlyBySpecifiedSkills(SkillDTOs skillDTOs) {
        List<String> listSkillsAsStrings = skillDTOs.getSkills().stream()
                .map(SkillDTO::getSkill)
                .map(Skill::name)
                .toList();

        return listSkillsAsStrings.stream()
                .map(jobAdvertisementDAO::findBySkill)
                .flatMap(List::stream)
                .distinct()
                .filter(jobAd -> new HashSet<>(listSkillsAsStrings).containsAll(
                        Arrays.stream(jobAd.getSkillsNeeded()
                                .split(";")).toList()))
                .toList();
    }

    public List<JobAdvertisement> findByLanguages(LanguageDTOs languageDTOs) {
        return languageDTOs.getLanguageDTOs().stream()
                .map(LanguageDTO::getLanguage)
                .map(Language::name)
                .map(jobAdvertisementDAO::findByLanguage)
                .flatMap(List::stream)
                .distinct()
                .toList();
    }

    public List<JobAdvertisement> findBySpecifiedLanguages(LanguageDTOs languageDTOs) {
        List<String> languagesAsStrings = languageDTOs.getLanguageDTOs().stream()
                .map(LanguageDTO::getLanguage)
                .map(Language::name)
                .toList();

        return languagesAsStrings.stream()
                .map(jobAdvertisementDAO::findByLanguage)
                .flatMap(List::stream)
                .distinct()
                .filter(jobAdvert -> new HashSet<>(languagesAsStrings).containsAll(
                        Arrays.stream(jobAdvert.getLanguages().split(";")).toList()
                ))
                .toList();
    }

    public List<JobAdvertisement> findJobAdvertisementByLocalization(LocalizationDTO localizationDTO) {
        Localization findLocalization = localizationService.findLocalization(localizationDTO);
        return jobAdvertisementDAO.findByLocalization(findLocalization);
    }

    public List<JobAdvertisement> findJobAdvertisementByExperience(ExperienceDTO experienceDTO) {
        int ordinal = experienceDTO.getExperience().ordinal();
        return Arrays.stream(Experience.values())
                .filter(x -> x.ordinal() <= ordinal)
                .map(Experience::getExperience)
                .map(jobAdvertisementDAO::findByExperience)
                .flatMap(List::stream)
                .distinct()
                .toList();
    }

    public List<JobAdvertisement> findJobAdvertisementBySalary(SalaryDTO salary) {
        return jobAdvertisementDAO.isSalaryBetweenRequiredSalary(salary.getSalary());
    }

    public List<JobAdvertisement> findJobAdvertisementBySeniority(SeniorityDTOs seniorityDTOs) {
        return seniorityDTOs.getSeniorityDTOs().stream()
                .map(SeniorityDTO::getSeniority)
                .map(Enum::name)
                .map(jobAdvertisementDAO::findBySeniority)
                .flatMap(List::stream)
                .distinct()
                .toList();
    }

    public List<JobAdvertisement> listOfSearchedJobAdvertisements(
            JobAdvertisementFinalFindQueryDTO finalQuery
    ) {
        List<JobAdvertisement> jobAdvertisementList = new ArrayList<>();

        if (finalQuery.getLocalizationDTO() != null) {
            jobAdvertisementList.addAll(findJobAdvertisementByLocalization(finalQuery.getLocalizationDTO()));
        }
        if (finalQuery.getFormOfWorkDTO() != null) {
            jobAdvertisementList.addAll(findByFormOfWork(finalQuery.getFormOfWorkDTO()));
        }
        if (finalQuery.getExperienceDTO() != null) {
            jobAdvertisementList.addAll(findJobAdvertisementByExperience(finalQuery.getExperienceDTO()));
        }
        if (finalQuery.getSalary() != null) {
            jobAdvertisementList.addAll(findJobAdvertisementBySalary(finalQuery.getSalary()));
        }
        if (finalQuery.getSeniorityDTOs() != null) {
            jobAdvertisementList.addAll(findJobAdvertisementBySeniority(finalQuery.getSeniorityDTOs()));
        }
        if (finalQuery.getSkillDTOs() != null) {
            if (finalQuery.getIsSpecifiedSkills()) {
                jobAdvertisementList.addAll(findOnlyBySpecifiedSkills(finalQuery.getSkillDTOs()));
            } else {
                jobAdvertisementList.addAll(findBySkills(finalQuery.getSkillDTOs()));
            }
        }

        if (finalQuery.getLanguageDTOs() != null) {
            if (finalQuery.getIsSpecifiedLanguages()) {
                jobAdvertisementList.addAll(findBySpecifiedLanguages(finalQuery.getLanguageDTOs()));
            } else {
                jobAdvertisementList.addAll(findByLanguages(finalQuery.getLanguageDTOs()));
            }
        }

        return jobAdvertisementList.stream()
                .distinct()
                .toList();
    }
}
