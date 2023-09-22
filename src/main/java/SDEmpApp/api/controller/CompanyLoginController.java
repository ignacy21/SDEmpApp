package SDEmpApp.api.controller;

import SDEmpApp.buisness.CompanyService;
import SDEmpApp.domain.Company;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class CompanyLoginController {

    private static final String COMPANY_LOGIN = "/company/login";

    private final CompanyService companyService;

    @RequestMapping(value = COMPANY_LOGIN, method = RequestMethod.GET)
    public String companyLoginPage(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "password", required = false) String password,
            Model model
    ) {
        Company company = companyService.findIfCanLogin(email, password);
        if (company == null) {
            model.addAttribute("no_account", "You don't have account have you?");
        } else {
            return "company_login_in";
        }
        return "company_login";
    }
}
