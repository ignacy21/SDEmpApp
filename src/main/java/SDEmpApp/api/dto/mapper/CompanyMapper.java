package SDEmpApp.api.dto.mapper;

import SDEmpApp.api.dto.CompanyDTO;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.Localization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    default Company mapToDTO(CompanyDTO companyDTO) {
        return Company.builder()
                .name(companyDTO.getName())
                .description(companyDTO.getDescription())
                .localization(Localization.builder()
                        .cityName(companyDTO.getCityName())
                        .provinceName(companyDTO.getProvinceName())
                        .build())
                .jobAdvertisementEntities(companyDTO.getJobAdvertisementEntities())
                .build();
    }

    default CompanyDTO mapFromDTO(Company company) {
        Localization localization = company.getLocalization();
        return CompanyDTO.builder()
                .name(company.getName())
                .description(company.getDescription())
                .provinceName(localization.getProvinceName())
                .cityName(localization.getCityName())
                .jobAdvertisementEntities(company.getJobAdvertisementEntities())
                .build();
    }
}
