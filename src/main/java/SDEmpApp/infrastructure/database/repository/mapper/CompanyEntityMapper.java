package SDEmpApp.infrastructure.database.repository.mapper;

import SDEmpApp.domain.Company;
import SDEmpApp.infrastructure.database.entities.CompanyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyEntityMapper {

    default CompanyEntity mapToEntity(Company company) {
        if (company == null) {
            return null;
        }
        CompanyEntity.CompanyEntityBuilder companyEntity = CompanyEntity.builder();
        companyEntity.companyId(company.getCompanyId());
        companyEntity.name(company.getName());
        companyEntity.description(company.getDescription());
        companyEntity.email(company.getEmail());
        companyEntity.password(company.getPassword());
        companyEntity.localizationId(company.getLocalization().getLocalizationId());

        return companyEntity.build();
    }


    Company mapFromEntity(CompanyEntity id);
}
