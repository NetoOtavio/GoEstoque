package com.goestoque.goestoqueservice.auth;

import com.goestoque.goestoqueservice.config.JwtService;
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
        var user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(UserRole.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthResponseDTO(jwtToken);
    }

    public AuthResponseDTO authenticate(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = repository.findByEmail(request.email()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthResponseDTO(jwtToken);
    }
}
