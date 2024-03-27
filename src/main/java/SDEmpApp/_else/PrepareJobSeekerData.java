package SDEmpApp._else;

import SDEmpApp._else.data.DataForBootClasses;
import SDEmpApp.domain.JobSeeker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static SDEmpApp._else.data.DataForBootClasses.*;


@Component
@AllArgsConstructor
public class PrepareJobSeekerData {

    private static int counter = 0;
    private static int uniquePhone = 0;

    @Transactional
    public JobSeeker generateJobSeeker() {
        List<String> skills = DataForBootClasses.skills();
        List<String> languages = DataForBootClasses.languages();
        List<String> experiences = DataForBootClasses.experience();
        List<String> formsOfWork = List.of("HYBRID", "IN_PLACE", "REMOTE");
        List<String> formsOfEmployment = List.of("B2B", "NORMAL", "FREELANCE");


        String paddedNumberAsString = String.format("%09d", ++uniquePhone);
        String ph3 = paddedNumberAsString.substring(6, 9);
        String ph1 = paddedNumberAsString.substring(0, 3);
        String ph2 = paddedNumberAsString.substring(3, 6);
        return JobSeeker.builder()
                .name("name%s".formatted(++counter))
                .surname("surname%s".formatted(counter))
                .isStudent(randBoolean())
                .phone("+48 %s %s %s".formatted(ph1, ph2, ph3))
                .email("email%s.gmail.com".formatted(counter))
                .linkedin("https://www.linkedin.com/in/name%s-surname%s-123/".formatted(counter, counter))
                .git("https://github.com/username%s".formatted(counter))
                .languages(csvString(randIntInRange(1, 3), languages))
                .skills(csvString(randIntInRange(1, 5), skills))
                .formsOfEmployment(csvString(randIntInRange(1, 3), formsOfEmployment))
                .formsOfWork(csvString(randIntInRange(1, 3), formsOfWork))
                .experience(experiences.get(randIntInRange(0, experiences.size())))
                .aboutMe("I am great")
                .lookingForJob(randBoolean())
                .cv("cv%s".formatted(counter))
                .build();
    }
}
