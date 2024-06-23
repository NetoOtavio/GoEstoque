package com.goestoque.goestoqueservice.auth;

import com.goestoque.goestoqueservice.config.JwtService;
import com.goestoque.goestoqueservice.exception.UserAlreadyExistsException;
import com.goestoque.goestoqueservice.users.User;
import com.goestoque.goestoqueservice.users.UserRepository;
import com.goestoque.goestoqueservice.users.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO register(RegisterRequestDTO request) {
        if(repository.findByEmail(request.email()).isPresent()) throw new UserAlreadyExistsException();
        var user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(UserRole.USER)
                .isEnabled(true)
                .build();
        repository.save(user);
        return new AuthResponseDTO("Successfully Registered!");
    }

    public AuthResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        User user = repository.findByEmail(request.email()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthResponseDTO(jwtToken);
    }
}
