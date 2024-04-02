package SDEmpApp.api.controller;

import SDEmpApp.buisness.DAO.LocalizationDAO;
import SDEmpApp.exceptions.ErrorResponse;
import SDEmpApp.exceptions.NoSuchLocalizationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final LocalizationDAO localizationDAO;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handlerException(Exception ex) {
        String message = "Unexpected exception occurred: [%s]".formatted(ex.getMessage());
        log.error(message, ex);

        return new ErrorResponse(message);
    }
    @ExceptionHandler(NoSuchLocalizationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerNoSuchLocalizationException(NoSuchLocalizationException ex) {
        String localization = ex.getLocalization();

        return new ErrorResponse(localization);
    }

}
