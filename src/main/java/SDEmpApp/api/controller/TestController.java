package SDEmpApp.api.controller;

import SDEmpApp.api.dto.auxiliary.TestDTO;
import SDEmpApp.api.dto.auxiliary.enums.EmploymentType;
import SDEmpApp.api.dto.auxiliary.enums.Experience;
import SDEmpApp.api.dto.auxiliary.enums.FormOfWork;
import SDEmpApp.buisness.InputChecking;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Component
@RestController
@RequestMapping(value = "test")
@RequiredArgsConstructor
public class TestController {

    private final InputChecking inputChecking;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void findByUsername(
            @RequestBody TestDTO testDTO
    ) {
        List<String> testDTOs = testDTO.getTestDTOs();
        boolean b1 = inputChecking.checkInput(Experience.class, testDTOs);
        boolean b2 = inputChecking.checkInput(EmploymentType.class, testDTOs);
        boolean b3 = inputChecking.checkInput(FormOfWork.class, testDTOs);
        testDTO.getTestDTOs().forEach(System.err::println);
    }
}
