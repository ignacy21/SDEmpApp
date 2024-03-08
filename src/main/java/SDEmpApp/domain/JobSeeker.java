package SDEmpApp.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
@EqualsAndHashCode(of = "candidateId")
public class JobSeeker {

    Integer candidateId;
    String name;
    String surname;
    String studentNonStudent;
    String phone;
    String email;
    String linkedin;
    String git;
    String cv;
    String languages;
    String skills;
    String b2bNormalFit;
    String formOfWork;
    String experience;
    String aboutMe;
    String employedUnemployed;

}

