package com.goestoque.goestoqueservice.items;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;

    @PostMapping("/create")
    public ResponseEntity<ItemDTO> create(
            @RequestBody ItemDTO request
    ) {
        ItemDTO itemDTO = service.createItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemDTO);
    }

    @GetMapping("/readbyuser")
    public ResponseEntity<List<ItemDTO>>  readItemsByUser() {
        return ResponseEntity.ok(service.readItemsByUser());
    }

    @GetMapping("/readbyuserandcode")
    public ResponseEntity<ItemDTO> readItemByUserAndCode(
            @RequestParam String itemCode
    ) {
        return ResponseEntity.ok(service.readItemByUserAndCode(itemCode));
    }

    @GetMapping("/readall")
    public @ResponseBody List<ItemDTO> readAllItems() {
        return service.readAllItems();
    }

}
