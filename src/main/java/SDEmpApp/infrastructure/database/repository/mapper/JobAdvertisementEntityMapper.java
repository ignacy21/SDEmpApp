package SDEmpApp.infrastructure.database.repository.mapper;

import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobAdvertisementEntityMapper {

    JobAdvertisementEntity mapToEntity(JobAdvertisement jobAdvertisement);

    JobAdvertisement mapFromEntity(JobAdvertisementEntity jobAdvertisementEntity);
}
