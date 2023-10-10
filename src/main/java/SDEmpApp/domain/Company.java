package SDEmpApp.domain;

import lombok.*;

import java.util.List;

@With
@Value
@Builder
@ToString
@EqualsAndHashCode(of = "companyId")
public class Company {

    Integer companyId;
    String name;
    String description;
    Localization localization;
    List<JobAdvertisement> jobAdvertisements;
    String email;
    String password;
}