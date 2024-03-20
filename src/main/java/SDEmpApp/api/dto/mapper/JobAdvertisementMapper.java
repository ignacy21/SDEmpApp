package SDEmpApp.api.dto.mapper;

import SDEmpApp.api.dto.CompanyDTO;
import SDEmpApp.api.dto.JobAdvertisementDTO;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface JobAdvertisementMapper {

    JobAdvertisement mapFromDTO(JobAdvertisementDTO jobAdvertisement);
    @Mapping(target = "jobAdvertisements", ignore = true)
    Company companyDTOToCompany(CompanyDTO company);

    JobAdvertisementDTO mapToDTO(JobAdvertisement jobAdvertisement);

    @Mapping(target = "jobAdvertisements", ignore = true)
    CompanyDTO companyToCompanyDTO(Company company);

    @Mapping(target = "localization", ignore = true)
    JobAdvertisement mapJobAdvertisementDTOForUpdate(JobAdvertisementDTO jobAdvertisementDTO, @MappingTarget JobAdvertisement jobAdvertisement);
}
