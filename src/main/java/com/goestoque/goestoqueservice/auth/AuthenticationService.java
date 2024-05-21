package com.goestoque.goestoqueservice.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    public AuthResponseDTO register(RegisterRequestDTO request) {
        return null;
    }

    public AuthResponseDTO authenticate(AuthenticationRequestDTO request) {
        return null;
    }
}
