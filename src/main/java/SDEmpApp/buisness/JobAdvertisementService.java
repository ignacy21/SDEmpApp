package SDEmpApp.buisness;

import SDEmpApp.api.dto.auxiliary.*;
import SDEmpApp.api.dto.auxiliary.enums.Language;
import SDEmpApp.api.dto.auxiliary.enums.Skill;
import SDEmpApp.api.dto.finalQueriesDTO.JobAdvertisementFinalFindQueryDTO;
import SDEmpApp.buisness.DAO.JobAdvertisementDAO;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.domain.Localization;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

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

    public List<JobAdvertisement> criteriaApiFindQuery(
            Localization localization,
            String formOfWork,
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
                localizationService.findLocalization(finalQuery.getLocalizationDTO()) :
                null;
        String formOfWork = finalQuery.getFormOfWorkDTO() != null ?
                finalQuery.getFormOfWorkDTO().getFormOfWork().name() :
                null;
        Integer ordinal = finalQuery.getExperienceDTO() != null ?
                finalQuery.getExperienceDTO().getExperience().ordinal() :
                null;
        BigDecimal salary = finalQuery.getSalary() != null ?
                finalQuery.getSalary().getSalary() :
                null;
        List<String> seniorityList = finalQuery.getSeniorityDTOs() != null ?
                finalQuery.getSeniorityDTOs().getSeniorityDTOs().stream()
                        .map(SeniorityDTO::getSeniority)
                        .map(Enum::name)
                        .toList() :
                null;

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
        LanguageDTOs languageDTOs = finalQuery.getLanguageDTOs();
        if (languageDTOs != null) {
            List<String> languages = finalQuery.getLanguageDTOs().getLanguageDTOs().stream()
                    .map(LanguageDTO::getLanguage)
                    .map(Language::name)
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
        SkillDTOs skillDTOs = finalQuery.getSkillDTOs();
        if (skillDTOs != null) {
            List<String> skills = finalQuery.getSkillDTOs().getSkills().stream()
                    .map(SkillDTO::getSkill)
                    .map(Skill::name)
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
