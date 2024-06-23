package com.goestoque.goestoqueservice.inputs;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/input")
@RequiredArgsConstructor
public class InputController {

    private final InputService service;

    @PostMapping("/create")
    public ResponseEntity<InputDTO> create(
            @RequestBody Set<InputItemDTO> request
    ) {
        return ResponseEntity.ok(service.createInput(request));
    }
}
