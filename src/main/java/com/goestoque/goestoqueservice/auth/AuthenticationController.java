package com.goestoque.goestoqueservice.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO request
    ) {
        AuthResponseDTO responseDTO = service.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDTO> authenticate(
            @Valid @RequestBody AuthenticationRequestDTO request
    ) {
        AuthResponseDTO responseDTO = service.authenticate(request);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }


}
