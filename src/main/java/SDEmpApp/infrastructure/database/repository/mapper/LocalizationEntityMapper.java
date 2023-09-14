package SDEmpApp.infrastructure.database.repository.mapper;

import SDEmpApp.domain.Localization;
import SDEmpApp.infrastructure.database.entities.LocalizationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocalizationEntityMapper {

    LocalizationEntity mapToEntity(Localization localization);
    Localization mapFromEntity(LocalizationEntity localization);
}
