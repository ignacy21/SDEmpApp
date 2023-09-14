package SDEmpApp.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;

import java.util.List;

@With
@Value
@Builder
@EqualsAndHashCode(of = "companyId")
public class Company {

    Integer companyId;
    String name;
    String description;
    Localization localization;
    List<JobAdvertisement> jobAdvertisementEntities;
}