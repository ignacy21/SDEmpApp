package SDEmpApp.infrastructure.database.repository.mapper;

import SDEmpApp.domain.JobSeeker;
import SDEmpApp.infrastructure.database.entities.JobSeekerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobSeekerEntityMapper {

    JobSeekerEntity mapToEntity(JobSeeker jobSeeker);
    JobSeeker mapFromEntity(JobSeekerEntity jobSeeker);
}
