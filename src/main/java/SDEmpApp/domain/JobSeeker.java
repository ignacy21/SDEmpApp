package SDEmpApp.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.With;

@With
@Data
@Builder
@EqualsAndHashCode(of = "jobSeekerId")
public class JobSeeker {

    private Integer jobSeekerId;
    private String name;
    private String surname;
    private Boolean isStudent;
    private String phone;
    private String email;
    private String linkedin;
    private String git;
    private String cv;
    private String languages;
    private String skills;
    private String formsOfEmployment;
    private String formsOfWork;
    private String experience;
    private String aboutMe;
    private Boolean isEmployed;
    private Boolean lookingForJob;
    private Localization localization;

}

