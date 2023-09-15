package SDEmpApp.domain;

import lombok.*;

@With
@Value
@Builder
@EqualsAndHashCode(of = "skillId")
@ToString(of = {"skillId", "name"})
public class Skill {

    Integer skillId;
    String name;
}
