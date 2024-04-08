package SDEmpApp.buisness;

import SDEmpApp.api.dto.auxiliary.enums.FormOfWork;
import SDEmpApp.api.dto.auxiliary.enums.Language;
import SDEmpApp.api.dto.auxiliary.enums.Seniority;
import SDEmpApp.api.dto.auxiliary.enums.Skill;
import SDEmpApp.api.dto.finalQueriesDTO.JobAdvertisementFinalFindQueryDTO;
import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Localization;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class JobAdvertisementService {

    private final JobAdvertisementDAO jobAdvertisementDAO;

    private final LocalizationService localizationService;
    private final InputCheckingService inputCheckingService;

    public JobAdvertisement createJobAdvertisement(JobAdvertisement jobAdvertisement) {
        return jobAdvertisementDAO.createJobAdvertisement(jobAdvertisement);
    }

    public JobAdvertisement findById(Integer jobAdvertisementId) {
        return jobAdvertisementDAO.findById(jobAdvertisementId);
    }

    public JobAdvertisement updateJobAdvertisement(JobAdvertisement jobAdvertisement) {
        return jobAdvertisementDAO.updateJobAdvertisement(jobAdvertisement);
    }

    public List<JobAdvertisement> criteriaApiFindQuery(
            Localization localization,
            List<String> formOfWork,
            Integer experienceOrdinal,
            BigDecimal salary,
            List<String> seniorityList
    ) {
        return jobAdvertisementDAO.criteriaApiFindQuery(
                localization,
                formOfWork,
                experienceOrdinal,
                salary,
                seniorityList
        );
    }

    public List<JobAdvertisement> listOfSearchedJobAdvertisements(
            JobAdvertisementFinalFindQueryDTO finalQuery
    ) {
        Localization localization = finalQuery.getLocalizationDTO() != null ?
                localizationService.findLocalization(finalQuery.getLocalizationDTO()) : null;
        List<String> formOfWork = finalQuery.getFormOfWork() != null ?
                List.of(finalQuery.getFormOfWork()) : Collections.emptyList();
        Integer ordinal = finalQuery.getExperienceDTO() != null ?
                finalQuery.getExperienceDTO().getExperience().ordinal() : null;
        BigDecimal salary = finalQuery.getSalary() != null ? finalQuery.getSalary() : null;
        List<String> seniorityList = finalQuery.getSeniorities() != null ?
                finalQuery.getSeniorities() : Collections.emptyList();
        List<String> skills = finalQuery.getSkills() != null ? finalQuery.getSkills() : Collections.emptyList();
        List<String> languages = finalQuery.getLanguages() != null ?
                finalQuery.getLanguages() : Collections.emptyList();


        inputCheckingService.checkInput(FormOfWork.class, formOfWork);
        inputCheckingService.checkInput(Seniority.class, seniorityList);
        inputCheckingService.checkInput(Skill.class, skills);
        inputCheckingService.checkInput(Language.class, languages);

        List<JobAdvertisement> jobAdvertisements = criteriaApiFindQuery(
                localization,
                formOfWork,
                ordinal,
                salary,
                seniorityList
        );

        Predicate<JobAdvertisement> skillFilterPredicate = jobAdvertisementSkillPredicate(finalQuery);
        Predicate<JobAdvertisement> languageFilterPredicate = jobAdvertisementLanguagePredicate(finalQuery);

        jobAdvertisements = jobAdvertisements.stream()
                .filter(skillFilterPredicate)
                .filter(languageFilterPredicate)
                .toList();

        return jobAdvertisements;
    }

    private static Predicate<JobAdvertisement> jobAdvertisementLanguagePredicate(JobAdvertisementFinalFindQueryDTO finalQuery) {
        Predicate<JobAdvertisement> languageFilterPredicate = jobAdvertisement -> true;
        if (finalQuery.getLanguages() != null) {
            List<String> languages = finalQuery.getLanguages().stream()
                    .sorted()
                    .toList();
            if (finalQuery.getIsSpecifiedLanguages()) {
                languageFilterPredicate = jobAdv -> Arrays.stream(jobAdv.getLanguages().split(";"))
                        .allMatch(languages::contains);
            } else {
                languageFilterPredicate = jobAdv -> Arrays.stream(jobAdv.getLanguages().split(";"))
                        .anyMatch(languages::contains);
            }
        }
        return languageFilterPredicate;
    }

    private static Predicate<JobAdvertisement> jobAdvertisementSkillPredicate(JobAdvertisementFinalFindQueryDTO finalQuery) {
        Predicate<JobAdvertisement> skillFilterPredicate = jobAdvertisement -> true;
        if (finalQuery.getSkills() != null) {
            List<String> skills = finalQuery.getSkills().stream()
                    .sorted()
                    .toList();
            if (finalQuery.getIsSpecifiedSkills()) {
                skillFilterPredicate = jobAdv -> Arrays.stream(jobAdv.getSkillsNeeded().split(";"))
                        .allMatch(skills::contains);
            } else {
                skillFilterPredicate = jobAdv -> Arrays.stream(jobAdv.getSkillsNeeded().split(";"))
                        .anyMatch(skills::contains);
            }
        }
        return skillFilterPredicate;
    }

    public String sortCsv(String csvString) {
        return Arrays.stream(csvString.split(";"))
                .sorted()
                .reduce("%s;%s"::formatted)
                .orElseThrow();
    }
}
