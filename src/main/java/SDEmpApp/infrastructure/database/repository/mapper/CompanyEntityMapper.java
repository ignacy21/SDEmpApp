package SDEmpApp.infrastructure.database.repository.mapper;

import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import SDEmpApp.infrastructure.database.entities.CompanyEntity;
import SDEmpApp.infrastructure.database.entities.JobAdvertisementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyEntityMapper {

    CompanyEntity mapToEntity(Company company);

    @Mapping(target = "company", ignore = true)
    JobAdvertisement jobAdvertisementEntityToJobAdvertisement(JobAdvertisementEntity jobAdvertisementEntity);
    @Mapping(target = "company", ignore = true)
    JobAdvertisementEntity jobJobAdvertisementToAdvertisementEntity(JobAdvertisement jobAdvertisement);

    Company mapFromEntity(CompanyEntity companyEntity);
}
