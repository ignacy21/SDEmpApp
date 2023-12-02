package SDEmpApp.infrastructure.database.mapper;

import SDEmpApp.domain.Candidate;
import SDEmpApp.infrastructure.database.entities.CandidateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateEntityMapper {

    CandidateEntity mapToEntity(Candidate candidate);
}
