package SDEmpApp.infrastructure.database.repository.mapper;

import SDEmpApp.domain.Company;
import SDEmpApp.domain.Localization;
import SDEmpApp.infrastructure.database.entities.CompanyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;

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


    default Company mapFromEntity(CompanyEntity companyEntity) {
        if (companyEntity == null) {
            return null;
        }

        Company.CompanyBuilder company = Company.builder();

        company.companyId(companyEntity.getCompanyId());
        company.name(companyEntity.getName());
        company.description(companyEntity.getDescription());
        company.email(companyEntity.getEmail());
        company.password(companyEntity.getPassword());
        company.localization(Localization.builder()
                .localizationId(companyEntity.getLocalizationId())
                .build());
        company.jobAdvertisements(Collections.emptyList());

        return company.build();
    }
}
