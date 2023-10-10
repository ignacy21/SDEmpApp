package SDEmpApp.api.controller;

import SDEmpApp.api.controller.data.ControllerData;
import SDEmpApp.api.dto.JobAdvertisementDTO;
import SDEmpApp.api.dto.mapper.JobAdvertisementMapper;
import SDEmpApp.buisness.CompanyService;
import SDEmpApp.buisness.JobAdvertisementService;
import SDEmpApp.buisness.LocalizationService;
import SDEmpApp.domain.Company;
import SDEmpApp.domain.JobAdvertisement;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
@AllArgsConstructor
public class CompanyLoginController {

    private static final String COMPANY_LOGIN = "/company/login";
    private static final String ADD_JOB_ADVERTISEMENT = "/addJobAdvertisement";

    private final JobAdvertisementMapper jobAdvertisementMapper;

    private final CompanyService companyService;
    private final LocalizationService localizationService;
    private final JobAdvertisementService jobAdvertisementService;

    @RequestMapping(value = COMPANY_LOGIN, method = RequestMethod.GET)
    public String companyLogin(
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "password", required = false) String password,
            @ModelAttribute("jobAdvertisementDTO") JobAdvertisementDTO jobAdvertisementDTO,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        Company company = companyService.findIfCanLogin(email, password);
        // TODO
        if (Objects.isNull(company)) {

        } else {
            // job advertisement attributes
            model.addAttribute("provinces", ControllerData.provinces());
            model.addAttribute("cities", localizationService.findCities());
            model.addAttribute("languages", ControllerData.languages());
            model.addAttribute("languageLevels", ControllerData.languageLevels());
            model.addAttribute("skills", ControllerData.skills());
            model.addAttribute("formsOfWork", ControllerData.formsOfWork());
            model.addAttribute("ids", company.getCompanyId());

            // view company's job advertisements in table
            List<JobAdvertisementDTO> jobAdvertisements = company.getJobAdvertisements().stream()
                    .map(jobAd -> jobAdvertisementMapper.mapToDTO(jobAd, company))
                    .toList();
            model.addAttribute("jobAdvertisements", jobAdvertisements);
        }

        return "company_login";
    }

    @RequestMapping(value = ADD_JOB_ADVERTISEMENT, method = RequestMethod.POST)
    public String createJobAdvertisement(
            @ModelAttribute("jobAdvertisementDTO") JobAdvertisementDTO jobAdvertisementDTO,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        Company company = companyService.findCompanyById(Integer.parseInt(jobAdvertisementDTO.getCompanyId()));
        JobAdvertisement jobAdvertisement = jobAdvertisementMapper.mapFromDTO(jobAdvertisementDTO, company);
        jobAdvertisementService.createJobAdvertisement(jobAdvertisement);

        return "redirect:/company/login";
    }
}
