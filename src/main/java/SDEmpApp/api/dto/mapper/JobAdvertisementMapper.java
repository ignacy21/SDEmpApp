package SDEmpApp.api.dto.mapper;

import SDEmpApp.api.dto.JobAdvertisementDTO;
import SDEmpApp.domain.JobAdvertisement;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobAdvertisementMapper {

    JobAdvertisement mapFromDTO(JobAdvertisementDTO jobAdvertisement);

    JobAdvertisementDTO mapToDTO(JobAdvertisement jobAdvertisement);
}
