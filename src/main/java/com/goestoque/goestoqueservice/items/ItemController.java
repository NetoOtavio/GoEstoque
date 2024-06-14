package com.goestoque.goestoqueservice.items;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;

    @PostMapping("/create")
    public ResponseEntity<CreateResponseDTO> create(
            @RequestBody CreateRequestDTO request
    ) {

        return ResponseEntity.ok(service.createItem(request));
    }

    @GetMapping("/readbyuser")
    public ResponseEntity<List<ItemResponseDTO>>  readItemsByUser() {
        return ResponseEntity.ok(service.readItemsByUser());
    }

    /*@GetMapping("/readbyuser")
    public ResponseEntity<Set<Item>>  readItemsByUser() {
        return ResponseEntity.ok(service.readUserItems());
    }*/

    @GetMapping("/readall")
    public @ResponseBody List<ItemResponseDTO> readAllItems() {
        return service.findAllItems();
    }

}
