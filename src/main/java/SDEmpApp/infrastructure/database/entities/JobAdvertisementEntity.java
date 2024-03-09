package SDEmpApp.infrastructure.database.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString(of = {"jobAdvertisementId", "localizationId", "languages", "skillsNeeded", "duties", "formOfWork"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "jobAdvertisementId")
@Table(name = "job_advertisement")
public class JobAdvertisementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_advertisement_id")
    private Integer jobAdvertisementId;

    @Column(name = "localization_id")
    private Integer localizationId;

    @Column(name = "languages")
    private String languages;

    @Column(name = "skills")
    private String skillsNeeded;

    @Column(name = "duties")
    private String duties;

    @Column(name = "form_of_work")
    private String formOfWork;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;


}
