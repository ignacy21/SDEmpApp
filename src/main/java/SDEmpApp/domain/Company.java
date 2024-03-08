package SDEmpApp.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.With;

import java.util.List;

@With
@Data
@Builder
@EqualsAndHashCode(of = "companyId")
public class Company {

    private Integer companyId;
    private String name;
    private String description;
    private Localization localization;
    private List<JobAdvertisement> jobAdvertisements;
    private String email;
    private String password;
}