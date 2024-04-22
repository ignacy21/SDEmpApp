package SDEmpApp.JWT.demo;

import SDEmpApp.JWT.config.JwtService;
import SDEmpApp.JWT.user.User;
import SDEmpApp.JWT.user.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public User findUser(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("There is no User with id[%s]".formatted(id))
        );
    }

    public User userData(String token) {
        Claims claims = jwtService.extractAllClaims(token);
        String email = claims.getSubject();
        return userRepository.findByEmail(email).orElseThrow();
    }

    public void updateData(User user) {
        userRepository.save(user);
    }
}
