package SDEmpApp.infrastructure.database.entities;

import SDEmpApp.domain.Localization;
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
@EqualsAndHashCode(of = "companyId")
@Table(name = "company")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "localization_id")
    private Integer localizationId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "company")
    private List<JobAdvertisementEntity> jobAdvertisementEntities;
}
