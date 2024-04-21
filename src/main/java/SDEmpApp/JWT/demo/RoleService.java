package SDEmpApp.JWT.demo;

import SDEmpApp.JWT.user.RoleEntity;
import SDEmpApp.JWT.user.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService  {

    private final RoleRepository roleRepository;

    public RoleEntity findByName(String name) {
        return roleRepository.findByRole(name).orElseThrow(
                () -> new RuntimeException("No such role [%s]".formatted(name))
        );
    }
}
