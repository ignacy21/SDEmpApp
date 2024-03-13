package SDEmpApp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoSuchLocalizationException extends RuntimeException {

    private final String localization;

}
