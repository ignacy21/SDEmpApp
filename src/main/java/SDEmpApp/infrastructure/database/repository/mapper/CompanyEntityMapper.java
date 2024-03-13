package SDEmpApp.infrastructure.database.repository.mapper;

import SDEmpApp.domain.Company;
import SDEmpApp.domain.Localization;
import SDEmpApp.infrastructure.database.entities.CompanyEntity;
import SDEmpApp.infrastructure.database.entities.LocalizationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyEntityMapper {

    CompanyEntity mapToEntity(Company company);
//    default CompanyEntity mapToEntity(Company company) {
//        if (company == null) {
//            return null;
//        }
//        Localization localization = company.getLocalization();
//        CompanyEntity.CompanyEntityBuilder companyEntity = CompanyEntity.builder();
//        companyEntity.companyId(company.getCompanyId());
//        companyEntity.name(company.getName());
//        companyEntity.description(company.getDescription());
//        companyEntity.email(company.getEmail());
//        companyEntity.password(company.getPassword());
//
//        return companyEntity.build();
//    }


    Company mapFromEntity(CompanyEntity companyEntity);
//    default Company mapFromEntity(CompanyEntity companyEntity) {
//        if (companyEntity == null) {
//            return null;
//        }
//
//        LocalizationEntity localization = companyEntity.getLocalization();
//
//        Company.CompanyBuilder company = Company.builder();
//
//        company.companyId(companyEntity.getCompanyId());
//        company.name(companyEntity.getName());
//        company.description(companyEntity.getDescription());
//        company.email(companyEntity.getEmail());
//        company.password(companyEntity.getPassword());
//        company.jobAdvertisements(Collections.emptyList());
//
//        return company.build();
//    }
}
