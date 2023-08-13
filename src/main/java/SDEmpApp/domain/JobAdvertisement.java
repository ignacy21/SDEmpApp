package SDEmpApp.domain;

import lombok.*;


@With
@Value
@Builder
@EqualsAndHashCode(of = "jobAdvertisementId")
@ToString(of = {"jobAdvertisementId", "localization", "languages", "skillsNeeded", "duties", "formOfWork"})
public class JobAdvertisement {

    Integer jobAdvertisementId;
    String localization;
    String languages;
    String skillsNeeded;
    String duties;
    String formOfWork;
    Company company;

}
