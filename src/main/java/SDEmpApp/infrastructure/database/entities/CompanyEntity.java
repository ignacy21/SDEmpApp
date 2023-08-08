package SDEmpApp.infrastructure.database.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer company_id;

    @Column(name = "name")
    private String name;

    @Column(name = "localization")
    private String localization;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<JobAdvertisementEntity> jobAdvertisementEntities;
}
