package SDEmpApp.infrastructure.database.repository.mapper;

import SDEmpApp.infrastructure.database.entities.SkillEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkillEntityMapper {

    SkillEntity mapToEntity(Skill skill);
}
