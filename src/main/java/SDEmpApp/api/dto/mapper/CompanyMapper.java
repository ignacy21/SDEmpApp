package SDEmpApp.api.dto.mapper;

import SDEmpApp.api.dto.CompanyDTO;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.Localization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    default Company map(CompanyDTO companyDTO) {
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
}
