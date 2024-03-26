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
import SDEmpApp.infrastructure.database.repository.mapper.LocalizationEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class JobAdvertisementService {
    private final JobAdvertisementDAO jobAdvertisementDAO;
    private final LocalizationService localizationService;

    private final LocalizationEntityMapper localizationEntityMapper;

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
                localizationService.findLocalization(finalQuery.getLocalizationDTO()) : null;
        String formOfWork = finalQuery.getFormOfWorkDTO() != null ?
                finalQuery.getFormOfWorkDTO().getFormOfWork().name() : null;
        Integer ordinal = finalQuery.getExperienceDTO() != null ?
                finalQuery.getExperienceDTO().getExperience().ordinal() : null;
        BigDecimal salary = finalQuery.getSalary() != null ?
                finalQuery.getSalary().getSalary() : null;
        List<String> seniorityList = finalQuery.getSeniorityDTOs() != null ?
                finalQuery.getSeniorityDTOs().getSeniorityDTOs().stream()
                        .map(SeniorityDTO::getSeniority)
                        .map(Enum::name)
                        .toList() : null;
        List<JobAdvertisement> jobAdvertisements = criteriaApiFindQuery(
                localization,
                formOfWork,
                ordinal,
                salary,
                seniorityList
        );
        Predicate<JobAdvertisement> skillFilterPredicate = jobAdvertisement -> true;
        if (finalQuery.getSkillDTOs() != null) {
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
                        .map(skills::contains)
                        .toList()
                        .contains(true);
            }
        }
        jobAdvertisements = jobAdvertisements.stream()
                .filter(skillFilterPredicate)
                .toList();

        // TODO find by language

        return jobAdvertisements;
    }

    public String sortCsv(String csvString) {
        return Arrays.stream(csvString.split(";"))
                .sorted()
                .reduce((l1, l2) -> l1 + ";" + l2)
                .orElseThrow();
    }
}
