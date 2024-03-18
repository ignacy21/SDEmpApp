package SDEmpApp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class JobSeekerDTOs {

    List<JobSeekerDTO> jobSeekerDTOs;
}
