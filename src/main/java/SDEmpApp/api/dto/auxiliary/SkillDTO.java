package SDEmpApp.api.dto.auxiliary;

import SDEmpApp.api.dto.auxiliary.enums.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillDTO {

    private Skill skill;
}