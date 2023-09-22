package SDEmpApp.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
public class CompanyController {

    private static final String HOME = "/company/login/in";


    @RequestMapping(value = HOME, method = RequestMethod.GET)
    public String homePage() {
        return "company_login_in";
    }
}
