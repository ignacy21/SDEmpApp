package SDEmpApp.infrastructure.database.mapper;

import SDEmpApp.domain.Skill;
import SDEmpApp.infrastructure.database.entities.SkillEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-12T13:38:54+0100",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.0.jar, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class SkillEntityMapperImpl implements SkillEntityMapper {

    @Override
    public SkillEntity mapToEntity(Skill skill) {
        if ( skill == null ) {
            return null;
        }

        SkillEntity.SkillEntityBuilder skillEntity = SkillEntity.builder();

        skillEntity.skillId( skill.getSkillId() );
        skillEntity.name( skill.getName() );

        return skillEntity.build();
    }
}
