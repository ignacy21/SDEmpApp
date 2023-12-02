package SDEmpApp.infrastructure.database.mapper;

import SDEmpApp.domain.Skill;
import SDEmpApp.infrastructure.database.entities.SkillEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillEntityMapper {

    SkillEntity mapToEntity(Skill skill);
}
