package SDEmpApp.api.dto.mapper;

import SDEmpApp.api.dto.JobSeekerDTO;
import SDEmpApp.domain.JobSeeker;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobSeekerMapper {

    JobSeeker mapFromDTO(JobSeekerDTO jobSeekerDTO);
}
