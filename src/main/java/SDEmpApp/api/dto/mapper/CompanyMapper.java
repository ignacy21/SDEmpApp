package SDEmpApp.api.dto.mapper;

import SDEmpApp.api.dto.CompanyDTO;
import SDEmpApp.domain.Company;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CompanyMapper {

    CompanyDTO mapToDTO(Company companyDTO);

    @Mapping(target = "localization", ignore = true)
    @Mapping(target = "jobAdvertisements", ignore = true)
    Company mapDTOForUpdate(CompanyDTO companyDTO, @MappingTarget Company company);
}
