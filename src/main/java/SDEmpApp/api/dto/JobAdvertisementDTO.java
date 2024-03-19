package SDEmpApp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobAdvertisementDTO {

    private LocalizationDTO localization;
    private String languages;
    private String skillsNeeded;
    private String duties;
    private String formOfWork;
    private CompanyDTO company;
}
