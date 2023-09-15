package SDEmpApp.infrastructure.database.entities;

import jakarta.persistence.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "localization_id")
    private Integer localizationId;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "city_name")
    private String cityName;
}
