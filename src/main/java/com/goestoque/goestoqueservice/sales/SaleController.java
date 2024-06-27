package com.goestoque.goestoqueservice.sales;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService service;

    @PostMapping("/create")
    public ResponseEntity<SaleDTO> create(
            @RequestBody Set<SaleItemDTO> request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.convertSaleToSaleDTO( service.createSale(request)));
    }

    @GetMapping("/readsalesbyuser")
    public ResponseEntity<List<SaleDTO>> readSalesByUser() {
        return ResponseEntity.status(HttpStatus.OK).body(service.convertSaleListToSaleDTOList( service.readSalesByUser() ));
    }

    @GetMapping("/readsaleitemsbysale")
    public ResponseEntity<List<SaleItemDTO>> readSaleItemsBySale(
            @RequestParam String saleId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.convertSaleItemListToSaleItemDTOList( service.readSaleItemsBySale(saleId) ));
    }
}
