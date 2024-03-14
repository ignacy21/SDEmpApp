package SDEmpApp.api.dto;

import lombok.*;

import java.util.List;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CompaniesDTO {

    private List<CompanyDTO> companiesDTO;
}
