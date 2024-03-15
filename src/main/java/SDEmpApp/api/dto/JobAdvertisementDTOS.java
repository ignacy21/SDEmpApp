package SDEmpApp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class JobAdvertisementDTOS {

    private List<JobAdvertisementDTO> jobAdvertisementDTOs;
}
