package SDEmpApp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerDTO {

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
    private String b2bNormalFit;
    private String formOfWork;
    private String experience;
    private String aboutMe;
    private Boolean isEmployed;
    private Boolean lookingForJob;

}
