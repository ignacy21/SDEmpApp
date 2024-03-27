package SDEmpApp._else;

import SDEmpApp._else.data.DataForBootClasses;
import SDEmpApp.domain.JobAdvertisement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static SDEmpApp._else.data.DataForBootClasses.csvString;
import static SDEmpApp._else.data.DataForBootClasses.randIntInRange;


@Component
@AllArgsConstructor
public class PrepareJobAdvertisementData {

    private static int counter = 0;

    @Transactional
    public JobAdvertisement generateJobAdvertisement() {
        List<String> languages = DataForBootClasses.languages();
        List<String> skills = DataForBootClasses.skills();
        List<String> experiences = DataForBootClasses.experience();
        List<String> seniority = List.of("SENIOR", "JUNIOR", "EXPERT", "TRAINEE", "MID");
        List<String> formsOfWork = List.of("FIT", "HYBRID", "IN_PLACE", "REMOTE");

        return JobAdvertisement.builder()
                .languages(csvString(randIntInRange(1, 4), languages))
                .skillsNeeded(csvString(randIntInRange(1, 5), skills))
                .experienceNeeded(experiences.get(randIntInRange(0, experiences.size())))
                .duties("duties: %s".formatted(counter++))
                .formOfWork(formsOfWork.get(randIntInRange(0, formsOfWork.size())))
                .salaryFrom(new BigDecimal(randIntInRange(3, 5) * 1000))
                .salaryTo(new BigDecimal(randIntInRange(6, 20) * 1000))
                .seniority(seniority.get(randIntInRange(0, seniority.size())))
                .build();
    }


}
