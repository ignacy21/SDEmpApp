package SDEmpApp._else;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PrepareLocalizationData {

    public static void main(String[] args) {
        List<String> lines = new LinkedList<>();
        try {
            lines = Files.readAllLines(Path.of("src/main/resources/cities.md"));
        } catch (IOException e) {
            System.err.println("### ERROR");
        }

        int i = 0;
        for (String line : lines) {
            String[] split = line.split(" ");
            String reduce = Arrays.stream(split).limit(split.length - 1).reduce("", (x, y) -> x + " " + y);
            String province = whatProvince(split[split.length - 1]);
            System.out.printf("(%s, '%s', '%s')%n", i++, province, reduce.substring(1));
        }
    }


    private static String whatProvince(String string) {
        String result = "";
            result = switch (string) {
                case "(DŚ)" -> "Dolnośląskie";
                case "(KP)" -> "Kujawsko-pomorskie";
                case "(LU)" -> "Lubelskie";
                case "(LB)" -> "Lubuskie";
                case "(ŁD)" -> "Łódzkie";
                case "(MA)" -> "Małopolskie";
                case "(MZ)" -> "Mazowieckie";
                case "(OP)" -> "Opolskie";
                case "(PK)" -> "Podkarpackie";
                case "(PD)" -> "Podlaskie";
                case "(PM)" -> "Pomorskie";
                case "(ŚL)" -> "Śląskie";
                case "(ŚK)" -> "Świętokrzyskie";
                case "(WN)" -> "Warmińsko-mazurskie";
                case "(WP)" -> "Wielkopolskie";
                case "(ZP)" -> "Zachodniopomorskie";
                default -> throw new IllegalStateException("Unexpected value: " + string);
            };
        return result;
    }
}