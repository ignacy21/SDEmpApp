package SDEmpApp.buisness;

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

@Service
@AllArgsConstructor
public class JobSeekerService {

    private final JobSeekerDAO jobSeekerDAO;

    private final LocalizationService localizationService;
    private final InputCheckingService inputCheckingService;

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
        inputCheckingService.checkInput(FormOfWork.class, finalQuery.getFormsOfWork());
        inputCheckingService.checkInput(Skill.class, finalQuery.getSkills());
        inputCheckingService.checkInput(Language.class, finalQuery.getLanguages());

        Localization localization = finalQuery.getLocalization() != null ?
                localizationService.findLocalization(finalQuery.getLocalization()) : null;
        Boolean isLookingForJob = finalQuery.getIsLookingForJob() != null ?
                finalQuery.getIsLookingForJob() : null;
        Boolean isEmployed = finalQuery.getIsEmployed() != null ?
                finalQuery.getIsEmployed() : null;
        Boolean isStudent = finalQuery.getIsStudent() != null ?
                finalQuery.getIsStudent() : null;
        Integer experienceOrdinal = finalQuery.getExperience() != null ?
                finalQuery.getExperience().getExperience().ordinal() : null;

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

        return jobSeekers.stream()
                .filter(formsOfWorkPredicate)
                .filter(formsOfEmploymentPredicate)
                .filter(skillPredicate)
                .filter(languagePredicate)
                .toList();
    }

    private Predicate<JobSeeker> jobSeekersFormsOfWorkPredicate(List<String> formsOfWork) {
        Predicate<JobSeeker> languageFilterPredicate = jobAdvertisement -> true;
        if (formsOfWork != null) {
            languageFilterPredicate = jobSeeker -> Arrays.stream(jobSeeker.getFormsOfWork().split(";"))
                    .anyMatch(formsOfWork::contains);
        }
        return languageFilterPredicate;
    }
    private Predicate<JobSeeker> jobSeekersFormsOfEmploymentPredicate(List<String> formsOfEmployment) {
        Predicate<JobSeeker> languageFilterPredicate = jobAdvertisement -> true;
        if (formsOfEmployment != null) {
            languageFilterPredicate = jobSeeker -> Arrays.stream(jobSeeker.getFormsOfEmployment().split(";"))
                    .anyMatch(formsOfEmployment::contains);
        }
        return languageFilterPredicate;
    }

    private static Predicate<JobSeeker> jobJobSeekerSkillPredicate(JobSeekerFinalFindQueryDTO finalQuery) {
        Predicate<JobSeeker> skillFilterPredicate = jobAdvertisement -> true;
        List<String> skills = finalQuery.getSkills();
        if (skills != null) {
            List<String> sortedSkills = skills.stream()
                    .sorted()
                    .toList();
            if (finalQuery.getIfSpecifiedSkills()) {
                skillFilterPredicate = jobAdv -> Arrays.stream(jobAdv.getSkills().split(";"))
                        .allMatch(sortedSkills::contains)
                        &&
                        jobAdv.getSkills().split(";").length >= sortedSkills.size();
            } else {
                skillFilterPredicate = jobAdv -> Arrays.stream(jobAdv.getSkills().split(";"))
                        .anyMatch(sortedSkills::contains);
            }
        }
        return skillFilterPredicate;
    }

    private static Predicate<JobSeeker> jobAdvertisementLanguagePredicate(JobSeekerFinalFindQueryDTO finalQuery) {
        Predicate<JobSeeker> languageFilterPredicate = jobAdvertisement -> true;
        List<String> languages = finalQuery.getLanguages();
        if (languages != null) {
            List<String> sortedLanguages = languages.stream()
                    .sorted()
                    .toList();
            if (finalQuery.getIsSpecifiedLanguage()) {
                languageFilterPredicate = jobAdv -> Arrays.stream(jobAdv.getLanguages().split(";"))
                        .allMatch(sortedLanguages::contains)
                        &&
                        jobAdv.getLanguages().split(";").length >= sortedLanguages.size();
            } else {
                languageFilterPredicate = jobAdv -> Arrays.stream(jobAdv.getLanguages().split(";"))
                        .anyMatch(sortedLanguages::contains);
            }
        }
        return languageFilterPredicate;
    }
}
