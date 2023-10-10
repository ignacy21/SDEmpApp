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

    private String province;
    private String city;
    private String languages;
    private String duties;
    private String formOfWork;
    private String skillsNeeded;
    private String companyId;
}
