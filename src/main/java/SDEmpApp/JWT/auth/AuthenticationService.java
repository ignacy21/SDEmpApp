package SDEmpApp.JWT.auth;

import SDEmpApp.JWT.config.JwtService;
import SDEmpApp.JWT.user.RoleEntity;
import SDEmpApp.JWT.user.RoleRepository;
import SDEmpApp.JWT.user.User;
import SDEmpApp.JWT.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        RoleEntity roleEntity = roleRepository.findByRole(request.getRole()).orElseThrow();
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(roleEntity))
                .is_active(false)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        );
        Authentication authenticate = authenticationManager.authenticate(authentication);
        UserDetails principal = (User) authenticate.getPrincipal();
        var jwtToken = jwtService.generateToken(principal);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
