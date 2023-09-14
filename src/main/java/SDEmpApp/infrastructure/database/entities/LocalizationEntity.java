package SDEmpApp.infrastructure.database.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "localizationId")
@Table(name = "localization")
public class LocalizationEntity {

    @Column(name = "localization_id")
    private Integer localizationId;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "city_name")
    private String cityName;
}
