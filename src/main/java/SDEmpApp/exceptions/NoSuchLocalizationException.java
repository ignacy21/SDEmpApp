package SDEmpApp.exceptions;

import SDEmpApp.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoSuchLocalizationException extends RuntimeException {

    private final Company company;

}
