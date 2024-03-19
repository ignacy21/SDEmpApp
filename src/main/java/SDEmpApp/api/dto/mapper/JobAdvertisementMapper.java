package SDEmpApp.api.dto.mapper;

import SDEmpApp.api.dto.CompanyDTO;
import SDEmpApp.api.dto.JobAdvertisementDTO;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobAdvertisementMapper {

    JobAdvertisement mapFromDTO(JobAdvertisementDTO jobAdvertisement);
    @Mapping(target = "jobAdvertisements", ignore = true)
    Company companyDTOToCompany(CompanyDTO company);

    JobAdvertisementDTO mapToDTO(JobAdvertisement jobAdvertisement);

    @Mapping(target = "jobAdvertisements", ignore = true)
    CompanyDTO companyToCompanyDTO(Company company);
}
