package SDEmpApp.api.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@With
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
    private String seniority;
}
