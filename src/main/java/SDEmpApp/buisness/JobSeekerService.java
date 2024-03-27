package SDEmpApp.buisness;

import SDEmpApp.api.dto.auxiliary.*;
import SDEmpApp.api.dto.auxiliary.enums.EmploymentType;
import SDEmpApp.api.dto.auxiliary.enums.FormOfWork;
import SDEmpApp.api.dto.auxiliary.enums.Language;
import SDEmpApp.api.dto.auxiliary.enums.Skill;
import SDEmpApp.api.dto.finalQueriesDTO.JobSeekerFinalFindQueryDTO;
import SDEmpApp.buisness.DAO.JobSeekerDAO;
import SDEmpApp.domain.JobSeeker;
import SDEmpApp.domain.Localization;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
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

    public List<JobSeeker> findByUsername(String username) {
        return Arrays.stream(username.split(" "))
                .map(jobSeekerDAO::findJobSeekerByUsername)
                .flatMap(List::stream)
                .distinct()
                .toList();
    }

    private List<JobSeeker> criteriaApiFindQuery(
            Localization localization,
            Boolean isLookingForJob,
            Boolean isEmployed,
            Boolean isStudent,
            Integer experienceOrdinal
    ) {
        return jobSeekerDAO.criteriaApiFindQuery(
                localization,
                isLookingForJob,
                isEmployed,
                isStudent,
                experienceOrdinal
        );
    }

    public List<JobSeeker> listOfSearchedJobSeekers(JobSeekerFinalFindQueryDTO finalQuery) {
        Localization localization = finalQuery.getLocalization() != null ?
                localizationService.findLocalization(finalQuery.getLocalization()) :
                null;
        Boolean isLookingForJob = finalQuery.getIsLookingForJob() != null ?
                finalQuery.getIsLookingForJob() :
                null;
        Boolean isEmployed = finalQuery.getIsEmployed() != null ?
                finalQuery.getIsEmployed() :
                null;
        Boolean isStudent = finalQuery.getIsStudent() != null ?
                finalQuery.getIsStudent() :
                null;
        Integer experienceOrdinal = finalQuery.getExperience() != null ?
                finalQuery.getExperience().getExperience().ordinal() :
                null;

        List<JobSeeker> jobSeekers = criteriaApiFindQuery(
                localization,
                isLookingForJob,
                isEmployed,
                isStudent,
                experienceOrdinal
        );

        Predicate<JobSeeker> formsOfWorkPredicate = jobSeekersFormsOfWorkPredicate(finalQuery.getFormsOfWork());
        Predicate<JobSeeker> formsOfEmploymentPredicate = jobSeekersFormsOfEmploymentPredicate(finalQuery.getFormsOfEmployment());
        Predicate<JobSeeker> skillPredicate = jobJobSeekerSkillPredicate(finalQuery);
        Predicate<JobSeeker> languagePredicate = jobAdvertisementLanguagePredicate(finalQuery);

        List<JobSeeker> jobSeekers1 = jobSeekers.stream()
                .filter(formsOfWorkPredicate)
                .filter(formsOfEmploymentPredicate)
                .filter(skillPredicate)
                .filter(languagePredicate)
                .toList();
        return jobSeekers1;
    }

    private Predicate<JobSeeker> jobSeekersFormsOfWorkPredicate(FormOfWorkDTOs formsOfWork) {
        Predicate<JobSeeker> languageFilterPredicate = jobAdvertisement -> true;
        if (formsOfWork != null) {
            List<String> formsOfWorkStringList = formsOfWork.getFormOfWorkDTOs().stream()
                    .map(FormOfWorkDTO::getFormOfWork)
                    .map(FormOfWork::name)
                    .toList();
            languageFilterPredicate = jobSeeker -> Arrays.stream(jobSeeker.getFormsOfWork().split(";"))
                    .anyMatch(formsOfWorkStringList::contains);
        }
        return languageFilterPredicate;
    }
    private Predicate<JobSeeker> jobSeekersFormsOfEmploymentPredicate(EmploymentTypeDTOs formsOfEmployment) {
        Predicate<JobSeeker> languageFilterPredicate = jobAdvertisement -> true;
        if (formsOfEmployment != null) {
            List<String> formsOfEmploymentStringList = formsOfEmployment.getEmploymentTypeDTOs().stream()
                    .map(EmploymentTypeDTO::getEmploymentType)
                    .map(EmploymentType::name)
                    .toList();
            languageFilterPredicate = jobSeeker -> Arrays.stream(jobSeeker.getFormsOfEmployment().split(";"))
                    .anyMatch(formsOfEmploymentStringList::contains);
        }
        return languageFilterPredicate;
    }

    private static Predicate<JobSeeker> jobJobSeekerSkillPredicate(JobSeekerFinalFindQueryDTO finalQuery) {
        Predicate<JobSeeker> skillFilterPredicate = jobAdvertisement -> true;
        SkillDTOs skillDTOs = finalQuery.getSkills();
        if (skillDTOs != null) {
            List<String> skills = skillDTOs.getSkills().stream()
                    .map(SkillDTO::getSkill)
                    .map(Skill::name)
                    .sorted()
                    .toList();
            if (finalQuery.getIfSpecifiedSkills()) {
                skillFilterPredicate = jobAdv -> Arrays.stream(jobAdv.getSkills().split(";"))
                        .allMatch(skills::contains)
                        &
                        jobAdv.getSkills().split(";").length >= skills.size();
            } else {
                skillFilterPredicate = jobAdv -> Arrays.stream(jobAdv.getSkills().split(";"))
                        .anyMatch(skills::contains);
            }
        }
        return skillFilterPredicate;
    }

    private static Predicate<JobSeeker> jobAdvertisementLanguagePredicate(JobSeekerFinalFindQueryDTO finalQuery) {
        Predicate<JobSeeker> languageFilterPredicate = jobAdvertisement -> true;
        LanguageDTOs languageDTOs = finalQuery.getLanguageDTOs();
        if (languageDTOs != null) {
            List<String> languages = languageDTOs.getLanguageDTOs().stream()
                    .map(LanguageDTO::getLanguage)
                    .map(Language::name)
                    .sorted()
                    .toList();
            if (finalQuery.getIsSpecifiedLanguage()) {
                languageFilterPredicate = jobAdv -> Arrays.stream(jobAdv.getLanguages().split(";"))
                        .allMatch(languages::contains)
                        &
                        jobAdv.getLanguages().split(";").length >= languages.size();
            } else {
                languageFilterPredicate = jobAdv -> Arrays.stream(jobAdv.getLanguages().split(";"))
                        .anyMatch(languages::contains);
            }
        }
        return languageFilterPredicate;
    }


    private static List<Skill> getSkills(SkillDTOs skillDTOs) {
        return skillDTOs.getSkills().stream()
                .map(SkillDTO::getSkill)
                .collect(Collectors.toList());
    }
}
