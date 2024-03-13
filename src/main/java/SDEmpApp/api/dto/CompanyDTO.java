package SDEmpApp.api.dto;

import SDEmpApp.domain.JobAdvertisement;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDTO {
    String name;
    String description;
    String provinceName;
    String cityName;
    @Email
    String email;
    String password;
    List<JobAdvertisement> jobAdvertisementEntities;
}
