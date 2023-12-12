package SDEmpApp.domain;

import lombok.*;

import java.util.List;

@With
@Value
@Getter
@Builder
@EqualsAndHashCode(of = "companyId")
public class Company {

    Integer companyId;
    String name;
    String description;
    Localization localization;
    List<JobAdvertisement> jobAdvertisementEntities;
}