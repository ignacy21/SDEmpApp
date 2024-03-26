package SDEmpApp._else;

import SDEmpApp.domain.JobAdvertisement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Random;


@Component
@AllArgsConstructor
public class PrepareJobAdvertisementData {

    private static int counter = 0;

    @Transactional
    public JobAdvertisement generatedJobAdvertisement() {
        String[] skills = {"KOTLIN", "JAVA", "CPP", "SCALA", "HTML", "JAVASCRIPT", "C_SHARP", "AZURE", "GOLANG", "REACT_NATIVE", "PHP", "RUBY", "FLUTTER", "AWS", "ELIXIR", "C", "RUBY_ON_RAILS", "IOS", "SQL", "PYTHON", "REACT", "VUE_JS", "ANGULAR", "NET", "ANDROID"};
        String[] languages = {"KOREAN", "GERMAN", "DUTCH", "UKRAINIAN", "CHINESE", "BOSNIAN", "POLISH", "ALBANIAN", "CZECH", "SPANISH", "BULGARIAN", "FRENCH", "HUNGARIAN", "ITALIAN", "CROATIAN", "TURKISH", "ENGLISH_UK", "SLOVAK", "ENGLISH_US", "JAPANESE", "SERBIAN", "PORTUGUESE", "ROMANIAN", "FILIPINO", "FINNISH", "SLOVENE", "VIETNAMESE", "DANISH", "NORWEGIAN", "ESTONIAN", "WELSH", "GREEK", "LITHUANIAN", "SWEDISH"};
        String[] seniority = {"SENIOR", "JUNIOR", "EXPERT", "TRAINEE", "MID"};
        String[] formsOfWork = {"FIT", "HYBRID", "IN_PLACE", "REMOTE"};
        String[] experiences = {"0", "0 >= 1", "1 > 2", "2 > 5", "5 > "};

        return JobAdvertisement.builder()
                .languages(languages[randIntInRange(0, languages.length)])
                .skillsNeeded(skills[randIntInRange(0, skills.length)])
                .experienceNeeded(experiences[randIntInRange(0, experiences.length)])
                .duties("duties: %s".formatted(counter++))
                .formOfWork(formsOfWork[randIntInRange(0, formsOfWork.length)])
                .salaryFrom(new BigDecimal(randIntInRange(3, 5) * 1000))
                .salaryTo(new BigDecimal(randIntInRange(6, 20) * 1000))
                .seniority(seniority[randIntInRange(0, seniority.length)])
                .build();
    }

    private static int randIntInRange(int start, int end) {
        Random random = new Random();
        return random.nextInt(start, end);
    }
}
