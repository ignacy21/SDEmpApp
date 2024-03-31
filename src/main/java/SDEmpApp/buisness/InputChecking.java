package SDEmpApp.buisness;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class InputChecking {

    public <T extends Enum<T>> boolean checkInput(Class<T> enumClass, List<String> inputStringList) {
        boolean b = EnumSet.allOf(enumClass).containsAll(inputStringList);
        List<T> list = Arrays.asList(enumClass.getEnumConstants());
        return new HashSet<>(list).containsAll(inputStringList);
    }
}
