package SDEmpApp.infrastructure.database.repository.mapper;

import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.infrastructure.database.entities.CompanyEntity;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JobAdvertisementEntityMapper {

    JobAdvertisementEntity mapToEntity(JobAdvertisement jobAdvertisement);

    @Mapping(target = "jobAdvertisements", ignore = true)
    CompanyEntity companyToCompanyEntity(Company companyEntity);

    JobAdvertisement mapFromEntity(JobAdvertisementEntity jobAdvertisementEntity);

    @Mapping(target = "jobAdvertisements", ignore = true)
    Company companyEntityToCompany(CompanyEntity companyEntity);
}
