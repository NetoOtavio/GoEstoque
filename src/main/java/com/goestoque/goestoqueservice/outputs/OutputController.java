package com.goestoque.goestoqueservice.outputs;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/output")
@RequiredArgsConstructor
public class OutputController {

    private final OutputService service;

    @PostMapping("/create")
    public ResponseEntity<OutputDTO> create(
            @RequestBody Set<OutputItemDTO> request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.convertOutputToOutputDTO(service.createOutput(request)));
    }

    @GetMapping("/readoutputsbyuser")
    public ResponseEntity<List<OutputDTO>> readOutputsByUser() {
        return ResponseEntity.status(HttpStatus.OK).body(service.convertOutputListToOutputDTOList(service.readOutputsByUser()));
    }

    @GetMapping("/readoutputitemsbyoutput")
    public ResponseEntity<List<OutputItemDTO>> readOutputItemsByOutput(
            @RequestParam String outputId
    ) {
        List<OutputItemDTO> outputItemDTOS = service.convertOutputItemListToOutputItemDTOList(service.readOutputItemsByOutputId(outputId));
        return ResponseEntity.status(HttpStatus.OK).body(outputItemDTOS);
    }
}
