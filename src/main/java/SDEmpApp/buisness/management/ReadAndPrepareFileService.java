package SDEmpApp.buisness.management;

import lombok.Getter;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ReadAndPrepareFileService {

    private static final Map<String, Map<String, List<String>>> inputData;

    static {
        try {
            inputData = readFileContent();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Map<String, Map<String, List<String>>> readFileContent() throws IOException {
        Path path = ResourceUtils.getFile("classpath:SDEmpApp_usage_simulation.md").toPath();
        List<String> lines = Files.readAllLines(path).stream()
                .filter(line -> !line.startsWith("[//]: #"))
                .filter(line -> !line.isBlank())
                .toList();


        return lines.stream()
                .collect(Collectors.groupingBy(
                        line -> line.split("->")[0].trim(),
                        Collectors.toMap(
                                line -> line.split("->")[1].trim(),
                                line -> new ArrayList<>(List.of(line.substring(line.lastIndexOf("->") + 2).trim())),
                                (left, right) -> {
                                    left.addAll(right);
                                    return left;
                                })
                ));
    }

    public List<String> getData(Keys.MainCommand command1, Keys.SecondCommand command2) {
        return inputData.get(command1.toString()).get(command2.toString());
    }
}
