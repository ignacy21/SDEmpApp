package SDEmpApp.infrastructure.database.repository.mapper;

import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobAdvertisementEntityMapper {

    @Mapping(target = "company", ignore = true)
    JobAdvertisementEntity mapToEntity(JobAdvertisement jobAdvertisement);

    @Mapping(target = "company", ignore = true)
    JobAdvertisement mapFromEntity(JobAdvertisementEntity jobAdvertisementEntity);
}
