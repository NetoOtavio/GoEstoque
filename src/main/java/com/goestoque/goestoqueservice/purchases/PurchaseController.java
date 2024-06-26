package com.goestoque.goestoqueservice.purchases;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService service;
    @PostMapping("/create")
    public ResponseEntity<PurchaseDTO> create(
            @RequestBody Set<PurchaseItemDTO> request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.convertPurchaseToPurchaseDTO( service.createPurchase(request) ) );
    }
}
