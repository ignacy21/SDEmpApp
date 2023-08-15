package SDEmpApp.infrastructure.database.repository.mapper;

import SDEmpApp.domain.Company;
import SDEmpApp.infrastructure.database.entities.CompanyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyEntityMapper {


    CompanyEntity mapToEntity(Company company);

    Company mapFromEntity(CompanyEntity id);
}
