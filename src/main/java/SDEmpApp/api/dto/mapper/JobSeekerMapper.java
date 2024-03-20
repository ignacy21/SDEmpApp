package SDEmpApp.api.dto.mapper;

import SDEmpApp.api.dto.JobSeekerDTO;
import SDEmpApp.domain.JobSeeker;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface JobSeekerMapper {

    JobSeeker mapFromDTO(JobSeekerDTO jobSeekerDTO);
    JobSeekerDTO mapToDTO(JobSeeker jobSeekerDTO);

    @Mapping(target = "localization", ignore = true)
    JobSeeker mapDTOForUpdate(JobSeekerDTO jobSeekerDTO, @MappingTarget JobSeeker jobSeekerFind);
}
