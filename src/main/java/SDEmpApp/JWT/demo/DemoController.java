package SDEmpApp.JWT.demo;

import SDEmpApp.JWT.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main-page")
@RequiredArgsConstructor
public class DemoController {

    private final UserService userService;

    @GetMapping(value = "/get1")
    public UserData sayHelloToAll(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        return getUserData(authorizationHeader);
    }

    @GetMapping(value = "/get2")
    public UserData sayHelloToAmin(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        return getUserData(authorizationHeader);
    }

    @GetMapping(value = "/get3")
    public UserData sayHelloToUser(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        return getUserData(authorizationHeader);
    }

    private UserData getUserData(String authorizationHeader) {
        User user = userService.userData(authorizationHeader.substring("Bearer ".length()));
        return UserData.builder()
                .email(user.getEmail())
                .build();
    }
}
