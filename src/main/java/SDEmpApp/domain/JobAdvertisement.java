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
    private String formOfWork;
    private String experienceNeeded;
    private BigDecimal salaryFrom;
    private BigDecimal salaryTo;
    private String seniority;
    private String skillsNeeded;
    private String languages;
    private String duties;
    private Company company;

}
