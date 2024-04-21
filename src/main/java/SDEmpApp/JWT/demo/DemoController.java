package SDEmpApp.JWT.demo;

import SDEmpApp.JWT.user.RoleEntity;
import SDEmpApp.JWT.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/main-page")
@RequiredArgsConstructor
public class DemoController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping(value = "/get")
    public UserData sayHelloToAll(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        return getUserData(authorizationHeader);
    }

    @GetMapping(value = "/user")
    public UserData sayHelloToUser(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        return getUserData(authorizationHeader);
    }

    @GetMapping(value = "/job_seeker")
    public UserData sayHelloToJobSeeker(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        return getUserData(authorizationHeader);
    }

    @GetMapping(value = "/company")
    public UserData sayHelloToCompany(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        return getUserData(authorizationHeader);
    }
    @PutMapping(value = "/create-role-for/{id}")
    public ResponseEntity<User> addRole(
            @PathVariable Integer id,
            @Valid @RequestBody RoleRequest role
    ) {
        User user = userService.findUser(id);
        RoleEntity byName = roleService.findByName(role.getRoleName());

        Set<RoleEntity> roles = user.getRoles();
        roles.add(byName);
        user.setRoles(roles);
        userService.updateData(user);

        return ResponseEntity.ok().build();
    }


    private UserData getUserData(String authorizationHeader) {
        User user = userService.userData(authorizationHeader.substring("Bearer ".length()));
        return UserData.builder()
                .email(user.getEmail())
                .build();
    }
}
