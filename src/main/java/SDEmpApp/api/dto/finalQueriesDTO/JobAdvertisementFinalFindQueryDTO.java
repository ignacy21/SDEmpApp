package SDEmpApp.api.dto.finalQueriesDTO;

import SDEmpApp.api.dto.LocalizationDTO;
import SDEmpApp.api.dto.auxiliary.ExperienceDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobAdvertisementFinalFindQueryDTO {
    LocalizationDTO localizationDTO;
    String formOfWork;
    ExperienceDTO experienceDTO;
    BigDecimal salary;
    List<String> seniorities;

    List<String> skills;
    Boolean isSpecifiedSkills;

    List<String> languages;
    Boolean isSpecifiedLanguages;
}
