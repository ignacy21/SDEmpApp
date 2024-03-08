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
@EqualsAndHashCode(of = "candidateId")
@Table(name = "job_seeker")
public class JobSeekerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_seeker_id")
    private Integer candidateId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "is_student")
    private Boolean isStudent;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "linkedin")
    private String linkedin;

    @Column(name = "git")
    private String git;

    @Column(name = "cv")
    private String cv;

    @Column(name = "languages")
    private String languages;

    @Column(name = "skills")
    private String skills;

    @Column(name = "b2b_normal_fit")
    private String b2bNormalFit;

    @Column(name = "form_of_work")
    private String formOfWork;

    @Column(name = "experience")
    private String experience;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "is_employed")
    private Boolean isEmployed;

    @Column(name = "looking_for_job")
    private Boolean lookingForJob;
}
