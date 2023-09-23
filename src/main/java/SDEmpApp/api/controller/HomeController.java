package SDEmpApp.api.controller;

import SDEmpApp.api.dto.CompanyDTO;
import SDEmpApp.api.dto.mapper.CompanyMapper;
import SDEmpApp.buisness.CompanyService;
import SDEmpApp.domain.Company;
import SDEmpApp.exceptions.NoSuchCompanyException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@AllArgsConstructor
public class HomeController {

    private static final String HOME = "/";
    private static final String COMPANY_LOGIN = "/company/login";

    private final CompanyService companyService;

    @RequestMapping(value = HOME, method = RequestMethod.GET)
    public String homePage(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "password", required = false) String password
    ) {
        Company companyLoginData = companyService.findIfCanLogin(email, password);
        if (Objects.isNull(companyLoginData)) {
            return "home_page";
        } else {
            return companyLogin();
        }
    }

    @RequestMapping(value = COMPANY_LOGIN, method = RequestMethod.GET)
    public String companyLogin() {

        return "company_login";
    }
}
