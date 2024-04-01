package SDEmpApp.api.dto.finalQueriesDTO;

import SDEmpApp.api.dto.LocalizationDTO;
import SDEmpApp.api.dto.auxiliary.ExperienceDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerFinalFindQueryDTO {

    LocalizationDTO localization;
    Boolean isLookingForJob;
    Boolean isEmployed;
    ExperienceDTO experience;
    List<String> formsOfWork;
    List<String> formsOfEmployment;

    List<String> skills;
    Boolean ifSpecifiedSkills;

    List<String> languages;
    Boolean isSpecifiedLanguage;

    Boolean isStudent;
}
