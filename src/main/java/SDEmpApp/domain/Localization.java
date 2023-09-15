package SDEmpApp.domain;

import lombok.*;


@With
@Value
@Builder
@EqualsAndHashCode(of = "localizationId")
@ToString(of = {"localizationId", "provinceName", "cityName"})
public class Localization {

    Integer localizationId;
    String provinceName;
    String cityName;
}
