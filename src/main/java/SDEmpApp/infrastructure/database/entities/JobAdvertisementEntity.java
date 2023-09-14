package SDEmpApp.infrastructure.database.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString(of = {"jobAdvertisementId", "localization", "languages", "skillsNeeded", "duties", "formOfWork"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "jobAdvertisementId")
@Table(name = "job_advertisement")
public class JobAdvertisementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_advertisement_id")
    private Integer jobAdvertisementId;

    @Column(name = "localization")
    private String localization;

    @Column(name = "languages")
    private String languages;

    @Column(name = "duties")
    private String duties;

    @Column(name = "form_of_work")
    private String formOfWork;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "job_advertisement")
    private List<SkillEntity> skillsNeeded;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;


}
