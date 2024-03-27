package SDEmpApp._else.data;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class DataForBootClasses {

    public static List<String> provinces() {
        return List.of(
                "Dolnośląskie",
                "Kujawsko-pomorskie",
                "Lubelskie",
                "Lubuskie",
                "Łódzkie",
                "Małopolskie",
                "Mazowieckie",
                "Opolskie",
                "Podkarpackie",
                "Podlaskie",
                "Pomorskie",
                "Śląskie",
                "Świętokrzyskie",
                "Warmińsko-mazurskie",
                "Wielkopolskie",
                "Zachodniopomorskie");
    }

    public static List<String> skills() {
        return List.of(
                "JAVA",
                "PYTHON",
                ".NET",
                "C#",
                "SQL",
                "GOLANG",
                "SCALA",
                "KOTLIN",
                "C",
                "C++",
                "AZURE",
                "AWS",
                "ELIXIR",
                "REACT",
                "REACT NATIVE",
                "ANGULAR",
                "VUE.JS",
                "JAVASCRIPT",
                "HTML",
                "PHP",
                "RUBY",
                "RUBY ON RAILS",
                "ANDROID",
                "IOS",
                "FLUTTER"
        );
    }

    public static List<String> experience() {
        return List.of(
                "0",
                "0 >= 1",
                "1 > 2",
                "2 > 5",
                "5 > "
        );
    }

    public static List<String> languages() {
        return List.of(
                "POLISH",
                "ENGLISH(UK)",
                "HUNGARIAN",
                "CZECH",
                "FRENCH",
                "ALBANIAN",
                "BOSNIAN",
                "BULGARIAN",
                "CHINESE",
                "CROATIAN",
                "DANISH",
                "ESTONIAN",
                "FINNISH",
                "GERMAN",
                "GREEK",
                "ITALIAN",
                "JAPANESE",
                "KOREAN",
                "LITHUANIAN",
                "NORWEGIAN",
                "FILIPINO",
                "PORTUGUESE",
                "ROMANIAN",
                "SERBIAN",
                "SLOVAK",
                "SLOVENE",
                "SPANISH",
                "SWEDISH",
                "DUTCH",
                "ENGLISH(US)",
                "TURKISH",
                "UKRAINIAN",
                "VIETNAMESE",
                "WELSH"
        );
    }

    public static List<String> languageLevels() {
        return List.of(
                "Native",
                "A1",
                "A2",
                "B1",
                "B2",
                "C1",
                "C2"
        );
    }

    public static List<String> formsOfWork() {
        return List.of(
                "HYBRID",
                "IN_PLACE",
                "REMOTE",
                "FIT"
        );
    }

    public static List<String> prepareProvinces() {
        return List.of(
                "Dolnośląskie",
                "Kujawsko-pomorskie",
                "Lubelskie",
                "Lubuskie",
                "Łódzkie",
                "Małopolskie",
                "Mazowieckie",
                "Opolskie",
                "Podkarpackie",
                "Podlaskie",
                "Pomorskie",
                "Śląskie",
                "Świętokrzyskie",
                "Warmińsko-mazurskie",
                "Wielkopolskie",
                "Zachodniopomorskie");
    }

    public static String csvString(int howMany, List<String> list) {
        String[] resultArray = new String[howMany];
        for (int i = 0; i < howMany; i++) {
            resultArray[i] = list.get(randIntInRange(0, list.size()));
        }
        return toCsv(Arrays.stream(resultArray));
    }

    public static int randIntInRange(int start, int end) {
        Random random = new Random();
        return random.nextInt(start, end);
    }

    public static boolean randBoolean() {
        Random random = new Random();
        return random.nextInt(0, 2) == 1;

    }

    private static String toCsv(Stream<String> string) {
        return string
                .distinct()
                .reduce("%s;%s"::formatted)
                .orElseThrow();
    }
}
