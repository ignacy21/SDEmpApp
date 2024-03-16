package SDEmpApp.api.dto.auxiliary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class SkillDTOs {

    private List<SkillDTO> skills;
}
