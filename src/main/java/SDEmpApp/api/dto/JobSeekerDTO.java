package SDEmpApp.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerDTO {

    private String surname;
    private String name;
    private Boolean isStudent;

    @Pattern(regexp = "^[+]\\d{2}\\s\\d{3}\\s\\d{3}\\s\\d{3}$", message = "Invalid phone number")
    private String phone;

    @Email(message = "Invalid")
    private String email;

    @Pattern(regexp="^(https?://)?(www\\.)?linkedin\\.com/in/[a-zA-Z0-9-]+(/)?$", message="Invalid LinkedIn link")
    private String linkedin;

    @Pattern(regexp="^(https?://)?(www\\.)?github\\.com/[a-zA-Z0-9_-]+$", message="Invalid GitHub link")
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
    private LocalizationDTO localization;

}
