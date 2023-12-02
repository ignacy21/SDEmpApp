package SDEmpApp.infrastructure.database.mapper;

import SDEmpApp.domain.Localization;
import SDEmpApp.infrastructure.database.entities.LocalizationEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-02T17:48:36+0100",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.0.jar, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
public class LocalizationEntityMapperImpl implements LocalizationEntityMapper {

    @Override
    public LocalizationEntity mapToEntity(Localization localization) {
        if ( localization == null ) {
            return null;
        }

        LocalizationEntity.LocalizationEntityBuilder localizationEntity = LocalizationEntity.builder();

        localizationEntity.localizationId( localization.getLocalizationId() );
        localizationEntity.provinceName( localization.getProvinceName() );
        localizationEntity.cityName( localization.getCityName() );

        return localizationEntity.build();
    }

    @Override
    public Localization mapFromEntity(LocalizationEntity localization) {
        if ( localization == null ) {
            return null;
        }

        Localization.LocalizationBuilder localization1 = Localization.builder();

        localization1.localizationId( localization.getLocalizationId() );
        localization1.provinceName( localization.getProvinceName() );
        localization1.cityName( localization.getCityName() );

        return localization1.build();
    }
}
