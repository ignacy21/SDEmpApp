package SDEmpApp.api.dto;

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
    private String name;
    private LocalizationDTO localization;
    private String description;
    @Email
    private String email;
    private String password;
    private List<JobAdvertisementDTO> jobAdvertisements;
}
