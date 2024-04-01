package SDEmpApp.buisness;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;

@Service
@AllArgsConstructor
public class InputCheckingService {

    public <T extends Enum<T>> void checkInput(Class<T> enumClass, List<String> inputStringList) {
        List<String> list = EnumSet.allOf(enumClass).stream()
                .map(Enum::name)
                .toList();
        inputStringList.forEach(stringValue -> {
            if (!list.contains(stringValue)) {
                throw new RuntimeException("%s does not have input you choose [%s].%nChoose from the following: %s"
                        .formatted(enumClass.getSimpleName(), stringValue, list));
            }
        });
    }
}
