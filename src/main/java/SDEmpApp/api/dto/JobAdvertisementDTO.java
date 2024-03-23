package SDEmpApp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobAdvertisementDTO {

    private LocalizationDTO localization;
    private String languages;
    private String skillsNeeded;
    private String experienceNeeded;
    private String duties;
    private String formOfWork;
    private CompanyDTO company;
    private BigDecimal salaryFrom;
    private BigDecimal salaryTo;
}
