package SDEmpApp.api.controller;

import SDEmpApp.buisness.DAO.LocalizationDAO;
import SDEmpApp.domain.Localization;
import SDEmpApp.exceptions.NoSuchLocalizationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final LocalizationDAO localizationDAO;

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(Exception ex) {
        String message = "Unexpected exception occurred: [%s]".formatted(ex.getMessage());
        log.error(message, ex);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", message);
        return modelAndView;
    }
    @ExceptionHandler(NoSuchLocalizationException.class)
    public ModelAndView handlerNoSuchLocalizationException(NoSuchLocalizationException ex) {
        Localization localization = ex.getCompany().getLocalization();
        String message1 = "There is no such localization as:[%s, %s]"
                .formatted(localization.getProvinceName(), localization.getCityName());
        String message2 = "The province name should match city name.";
        String message3 = "If you live in: [%s] - as you selected, you should choose from cities below:"
                .formatted(localization.getProvinceName());
        List<String> listOfCitiesInSelectedProvince = listFormat(localizationDAO.findAvailableCitiesForProvince(localization.getProvinceName()));

        log.error(message1, ex);
        ModelAndView modelAndView = new ModelAndView("localization_error");
        modelAndView.addObject("errorMessage1", message1);
        modelAndView.addObject("errorMessage2", message2);
        modelAndView.addObject("errorMessage3", message3);
        modelAndView.addObject("listOfCities", listOfCitiesInSelectedProvince);
        return modelAndView;
    }

    private List<String> listFormat(List<Localization> list) {
        return list.stream()
                .map(Localization::getCityName)
                .toList();
    }


}
