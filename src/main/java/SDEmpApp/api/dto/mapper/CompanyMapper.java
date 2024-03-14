package SDEmpApp.api.dto.mapper;

import SDEmpApp.api.dto.CompanyDTO;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.Localization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    Company mapFromDTO(CompanyDTO companyDTO);
    CompanyDTO mapToDTO(Company companyDTO);


}
