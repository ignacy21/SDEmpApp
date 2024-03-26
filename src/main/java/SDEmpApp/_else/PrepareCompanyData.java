package SDEmpApp._else;

import SDEmpApp.domain.Company;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@AllArgsConstructor
public class PrepareCompanyData {

    private static int counter = 0;

    @Transactional
    public Company generateCompany() {
        return Company.builder()
                .name("company%s".formatted(counter))
                .description("description of company%s".formatted(counter))
                .email("company%s.emial@gmail.com".formatted(counter++))
                .password("pass")
                .build();
    }
}
