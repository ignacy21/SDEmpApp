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
public class JobAdvertisementFinalFindQueryDTO {
    LocalizationDTO localizationDTO;
    FormOfWorkDTO formOfWorkDTO;
    ExperienceDTO experienceDTO;
    SalaryDTO salary;
    SeniorityDTOs seniorityDTOs;

    SkillDTOs skillDTOs;
    Boolean isSpecifiedSkills;

    LanguageDTOs languageDTOs;
    Boolean isSpecifiedLanguages;
}
