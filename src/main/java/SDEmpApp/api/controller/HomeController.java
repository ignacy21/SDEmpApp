package SDEmpApp.api.controller;

import SDEmpApp.api.dto.CompanyDTO;
import SDEmpApp.api.dto.mapper.CompanyMapper;
import SDEmpApp.buisness.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class HomeController {

    private static final String HOME = "/";
    private static final String COMPANY_LOGIN = "/company/login";

    private final CompanyService companyService;

    @RequestMapping(value = HOME, method = RequestMethod.GET)
    public String homePage() {
        return "home_page";
    }

    @RequestMapping(value = COMPANY_LOGIN, method = RequestMethod.GET)
    public String companyLoginPage(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        companyService.findIfCanLogin(email, password);

        return "company_login";
    }
}
