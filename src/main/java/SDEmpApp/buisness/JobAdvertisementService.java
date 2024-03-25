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
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import SDEmpApp.infrastructure.database.entities.LocalizationEntity;
import SDEmpApp.infrastructure.database.repository.mapper.LocalizationEntityMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class JobAdvertisementService {
    private final JobAdvertisementDAO jobAdvertisementDAO;
    private final LocalizationService localizationService;

    private final LocalizationEntityMapper localizationEntityMapper;

    private final EntityManager entityManager;

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
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<JobAdvertisementEntity> criteriaQuery = criteriaBuilder.createQuery(JobAdvertisementEntity.class);
        Root<JobAdvertisementEntity> root = criteriaQuery.from(JobAdvertisementEntity.class);

        Predicate predicate = criteriaBuilder.conjunction();

        List<JobAdvertisement> jobAdvertisementList = new ArrayList<>();

        if (finalQuery.getLocalizationDTO() != null) {
            jobAdvertisementList.addAll(findJobAdvertisementByLocalization(finalQuery.getLocalizationDTO()));

            // Criteria API JPA
            Localization localization = localizationService.findLocalization(finalQuery.getLocalizationDTO());
            LocalizationEntity localizationEntity = localizationEntityMapper.mapToEntity(localization);
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("localization"), localizationEntity));
        }
        if (finalQuery.getFormOfWorkDTO() != null) {
            jobAdvertisementList.addAll(findByFormOfWork(finalQuery.getFormOfWorkDTO()));

            // Criteria API JPA
            String formOfWork = finalQuery.getFormOfWorkDTO().getFormOfWork().name();
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("formOfWork"), formOfWork));
        }
        if (finalQuery.getExperienceDTO() != null) {
            jobAdvertisementList.addAll(findJobAdvertisementByExperience(finalQuery.getExperienceDTO()));

            // Criteria API JPA
            int ordinal = finalQuery.getExperienceDTO().getExperience().ordinal();
            List<String> experiences = Arrays.stream(Experience.values())
                    .filter(x -> x.ordinal() <= ordinal)
                    .map(Experience::getExperience)
                    .toList();

            predicate = criteriaBuilder.and(predicate, root.get("experienceNeeded").in(experiences));
        }
        if (finalQuery.getSalary() != null) {
            jobAdvertisementList.addAll(findJobAdvertisementBySalary(finalQuery.getSalary()));

            // Criteria API JPA
            BigDecimal salary = finalQuery.getSalary().getSalary();
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("salaryFrom"), salary));
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("salaryTo"), salary));

        }
        if (finalQuery.getSeniorityDTOs() != null) {
            jobAdvertisementList.addAll(findJobAdvertisementBySeniority(finalQuery.getSeniorityDTOs()));

            // Criteria API JPA
            List<SeniorityDTO> seniorityDTOs = finalQuery.getSeniorityDTOs().getSeniorityDTOs();
            for (SeniorityDTO seniorityDTO : seniorityDTOs) {
                String seniority = seniorityDTO.getSeniority().name();
                predicate = criteriaBuilder.or(predicate, criteriaBuilder.equal(root.get("seniority"), seniority));
            }

//            List<String> list = seniorityDTOs.stream()
//                    .map(SeniorityDTO::getSeniority)
//                    .map(Enum::name)
//                    .peek(seniority ->
//                            criteriaQuery.select(root).where(criteriaBuilder.equal(
//                                    root.get("seniority"), seniority
//                            )))
//                    .toList();
        }
        if (finalQuery.getSkillDTOs() != null) {
            List<String> skills = finalQuery.getSkillDTOs().getSkills().stream()
                    .map(SkillDTO::getSkill)
                    .map(Skill::name)
                    .sorted()
                    .toList();

            if (finalQuery.getIsSpecifiedSkills()) {
                jobAdvertisementList.addAll(findOnlyBySpecifiedSkills(finalQuery.getSkillDTOs()));

                // Criteria API JPA
                String skillsReduceToOneString = skills.stream()
                        .reduce((a, b) -> a + ";" + b)
                        .orElseThrow();
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(
                        root.get("skillsNeeded"), skillsReduceToOneString)
                );
//                criteriaQuery.select(root).where(criteriaBuilder.equal(
//                        root.get("skillsNeeded"), skillsReduceToOneString)
//                );
            } else {
                jobAdvertisementList.addAll(findBySkills(finalQuery.getSkillDTOs()));

                // Criteria API JPA
                for (String skill : skills) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(
                            root.get("skillsNeeded"), skill));
                }
//                Stream<String> skillsNeeded = skills.stream()
//                        .peek(skill -> criteriaQuery.select(root).where(criteriaBuilder.like(
//                                root.get("skillsNeeded"), skill)
//                        ));
            }
        }

        if (finalQuery.getLanguageDTOs() != null) {
            List<String> languages = finalQuery.getLanguageDTOs().getLanguageDTOs().stream()
                    .map(LanguageDTO::getLanguage)
                    .map(Language::name)
                    .sorted()
                    .toList();
            if (finalQuery.getIsSpecifiedLanguages()) {
                jobAdvertisementList.addAll(findBySpecifiedLanguages(finalQuery.getLanguageDTOs()));

                // Criteria API JPA
                String skillsReduceToOneString = languages.stream()
                        .reduce((a, b) -> a + ";" + b)
                        .orElseThrow();
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(
                        root.get("languages"), skillsReduceToOneString)
                );

//                criteriaQuery.select(root).where(criteriaBuilder.equal(
//                        root.get("languages"), skillsReduceToOneString)
//                );
            } else {
                jobAdvertisementList.addAll(findByLanguages(finalQuery.getLanguageDTOs()));

                // Criteria API JPA
                for (String language : languages) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(
                            root.get("languages"), language)
                    );
                }
//                Stream<String> list = languages.stream()
//                        .peek(language -> criteriaQuery.select(root).where(criteriaBuilder.like(
//                                root.get("languages"), language)
//                        ));
            }
        }

        criteriaQuery.select(root).where(predicate);
        List<JobAdvertisementEntity> resultList = entityManager.createQuery(criteriaQuery).getResultList();

        return jobAdvertisementList.stream()
                .distinct()
                .toList();
    }

    public String sortCsv(String csvString) {
        return Arrays.stream(csvString.split(";"))
                .sorted()
                .reduce((l1, l2) -> l1 + ";" + l2)
                .orElseThrow();
    }
}
