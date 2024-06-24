package com.goestoque.goestoqueservice.purchases;

import com.goestoque.goestoqueservice.inputs.InputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final InputService inputService;

    /*
    public PurchaseDTO createPurchase(Set<PurchaseItemDTO> purchaseItemDTOS) {



        return new PurchaseDTO();
    }*/
}
