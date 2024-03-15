package SDEmpApp.api.dto.auxiliary;

import SDEmpApp.api.dto.auxiliary.enums.Experience;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ExperienceDTO {

    private Experience experience;
}
