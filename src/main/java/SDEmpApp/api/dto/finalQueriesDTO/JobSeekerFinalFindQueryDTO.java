package SDEmpApp.api.dto.finalQueriesDTO;

import SDEmpApp.api.dto.LocalizationDTO;
import SDEmpApp.api.dto.auxiliary.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerFinalFindQueryDTO {

    LocalizationDTO localization;
    Boolean isLookingForJob;
    Boolean isEmployed;
    ExperienceDTO experience;
    FormOfWorkDTOs formsOfWork;
    EmploymentTypeDTOs formsOfEmployment;

    SkillDTOs skills;
    Boolean ifSpecifiedSkills;

    LanguageDTOs languageDTOs;
    Boolean isSpecifiedLanguage;

    Boolean isStudent;
}
