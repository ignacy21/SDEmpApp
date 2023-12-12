package SDEmpApp.infrastructure.database.mapper;

import SDEmpApp.domain.Candidate;
import SDEmpApp.infrastructure.database.entities.CandidateEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-12T13:38:54+0100",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.0.jar, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class CandidateEntityMapperImpl implements CandidateEntityMapper {

    @Override
    public CandidateEntity mapToEntity(Candidate candidate) {
        if ( candidate == null ) {
            return null;
        }

        CandidateEntity.CandidateEntityBuilder candidateEntity = CandidateEntity.builder();

        candidateEntity.candidateId( candidate.getCandidateId() );
        candidateEntity.name( candidate.getName() );
        candidateEntity.surname( candidate.getSurname() );
        candidateEntity.studentNonStudent( candidate.getStudentNonStudent() );
        candidateEntity.phone( candidate.getPhone() );
        candidateEntity.email( candidate.getEmail() );
        candidateEntity.linkedin( candidate.getLinkedin() );
        candidateEntity.git( candidate.getGit() );
        candidateEntity.cv( candidate.getCv() );
        candidateEntity.languages( candidate.getLanguages() );
        candidateEntity.skills( candidate.getSkills() );
        candidateEntity.b2bNormalFit( candidate.getB2bNormalFit() );
        candidateEntity.formOfWork( candidate.getFormOfWork() );
        candidateEntity.experience( candidate.getExperience() );
        candidateEntity.aboutMe( candidate.getAboutMe() );
        candidateEntity.employedUnemployed( candidate.getEmployedUnemployed() );

        return candidateEntity.build();
    }
}
