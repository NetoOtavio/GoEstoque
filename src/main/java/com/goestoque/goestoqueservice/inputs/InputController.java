package com.goestoque.goestoqueservice.inputs;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/input")
@RequiredArgsConstructor
public class InputController {

    private final InputService inputService;

    @PostMapping("/create")
    public ResponseEntity<InputDTO> create(
            @RequestBody Set<InputItemDTO> request
    ) {
        return ResponseEntity.ok(inputService.convertToInputDTO( inputService.createInput(request) ) );
    }

    @GetMapping("/readinputsbyuser")
    public ResponseEntity<List<InputDTO>> readInputsByUser() {
        return ResponseEntity.status(HttpStatus.OK).body( inputService.convertToInputDTOList( inputService.readInputsByUser() ) );
    }

    @GetMapping("/readinputbyuserandid")
    public ResponseEntity<InputDTO> readInputByUserAndId(
            @RequestParam String inputId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body( inputService.convertToInputDTO( inputService.readInputByUserAndId(inputId) ) );
    }

    @GetMapping("/readinputitemsbyinput")
    public ResponseEntity<List<InputItemDTO>> readInputItemsByInput(
            @RequestParam String inputId
    ) {
        List<InputItemDTO> inputItemDTOList= inputService.convertToInputItemDTOList( inputService.readInputItemsByInput(inputId) );
        return ResponseEntity.status(HttpStatus.OK).body( inputItemDTOList );
    }
}
