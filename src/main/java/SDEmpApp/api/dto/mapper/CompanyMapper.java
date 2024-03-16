package SDEmpApp.api.dto.mapper;

import SDEmpApp.api.dto.CompanyDTO;
import SDEmpApp.domain.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyDTO mapToDTO(Company companyDTO);
//    default CompanyDTO mapToDTO(Company companyDTO) {
//        if (companyDTO == null) {
//            return null;
//        }
//
//        CompanyDTO.CompanyDTOBuilder companyDTO1 = CompanyDTO.builder();
//
//        companyDTO1.name(companyDTO.getName());
//        companyDTO1.description(companyDTO.getDescription());
//
//        Localization localization = companyDTO.getLocalization();
//        companyDTO1.provinceName(localization.getProvinceName());
//        companyDTO1.cityName(localization.getCityName());
//
//        companyDTO1.email(companyDTO.getEmail());
//        companyDTO1.password(companyDTO.getPassword());
//
//        return companyDTO1.build();
//    }


}
