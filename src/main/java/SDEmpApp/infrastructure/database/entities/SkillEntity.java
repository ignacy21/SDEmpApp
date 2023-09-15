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
@EqualsAndHashCode(of = "skillId")
@Table(name = "skill")
public class SkillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Integer skillId;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private JobAdvertisementEntity jobAdvertisement;
}
