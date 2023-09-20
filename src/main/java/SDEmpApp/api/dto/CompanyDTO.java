package SDEmpApp.api.dto;

import SDEmpApp.domain.JobAdvertisement;
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
    Integer companyId;
    String name;
    String description;
    String provinceName;
    String cityName;
    List<JobAdvertisement> jobAdvertisementEntities;
}
