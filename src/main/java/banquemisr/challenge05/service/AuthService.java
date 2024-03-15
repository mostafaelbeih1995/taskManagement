package banquemisr.challenge05.service;

import banquemisr.challenge05.model.entity.User;
import banquemisr.challenge05.model.enums.Role;
import banquemisr.challenge05.model.payload.AuthRequest;
import banquemisr.challenge05.model.payload.AuthResponse;
import banquemisr.challenge05.model.payload.RegisterRequest;
import banquemisr.challenge05.repository.UserRepository;
import banquemisr.challenge05.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthResponse register(RegisterRequest request){

        User user = User.builder()
                .firstName(request.getFirstName())
                .secondName(request.getSecondName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String token = jwtUtils.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse authenticate(AuthRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtUtils.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }
}
