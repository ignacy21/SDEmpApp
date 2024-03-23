package SDEmpApp.domain;

import lombok.*;

import java.math.BigDecimal;


@With
@Data
@Builder
@EqualsAndHashCode(of = "jobAdvertisementId")
@ToString(of = {"jobAdvertisementId", "localization", "languages", "skillsNeeded", "duties", "formOfWork"})
public class JobAdvertisement {

    private Integer jobAdvertisementId;
    private Localization localization;
    private String languages;
    private String skillsNeeded;
    private String experienceNeeded;
    private String duties;
    private String formOfWork;
    private Company company;
    private BigDecimal salaryFrom;
    private BigDecimal salaryTo;
    private String seniority;

}
