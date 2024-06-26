package com.goestoque.goestoqueservice.purchases;

import com.goestoque.goestoqueservice.inputs.Input;
import com.goestoque.goestoqueservice.inputs.InputItem;
import com.goestoque.goestoqueservice.inputs.InputItemDTO;
import com.goestoque.goestoqueservice.inputs.InputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final InputService inputService;

    @Transactional
    public Purchase createPurchase(Set<PurchaseItemDTO> purchaseItemDTOS) {
        Set<InputItemDTO> inputItemDTOS = convertPurchaseToInput(purchaseItemDTOS);
        Input input = inputService.createInput(inputItemDTOS);
        Purchase purchase = Purchase.builder()
                .input(input)
                .value(0)
                .build();
        purchaseRepository.save(purchase);
        for(PurchaseItemDTO purchaseItemDTO : purchaseItemDTOS) {
            InputItem inputItem = inputService.readInputItemByInputIdAndItemCode(input.getId(), purchaseItemDTO.itemCode());

            System.out.println("========================================================================================");
            System.out.println(inputItem.getId());
            System.out.println(inputItem);
            System.out.println(purchaseItemDTO.purchaseItemPrice());
            System.out.println(purchase);
            System.out.println("========================================================================================");

            PurchaseItem purchaseItem = PurchaseItem.builder()
                    .inputItem(inputItem)
                    .price(purchaseItemDTO.purchaseItemPrice())
                    .purchase(purchase)
                    .build();

            System.out.println("========================================================================================");
            System.out.println(purchase.toString());
            System.out.println("========================================================================================");

            purchaseItemRepository.save(purchaseItem);
            purchase.setValue(purchase.getValue() + purchaseItem.getPrice());
        }
        return purchaseRepository.save(purchase);
    }

    public Set<InputItemDTO> convertPurchaseToInput(Set<PurchaseItemDTO> purchaseItemDTOS) {
        return purchaseItemDTOS.stream()
                .map(this::convertPurchaseItemDTOToInputItemDTO)
                .collect(Collectors.toSet());
    }
    public InputItemDTO convertPurchaseItemDTOToInputItemDTO(PurchaseItemDTO purchaseItemDTO) {
        return new InputItemDTO(purchaseItemDTO.itemCode(), purchaseItemDTO.amount());
    }

    public PurchaseDTO convertPurchaseToPurchaseDTO(Purchase purchase) {
        return new PurchaseDTO(
                purchase.getId(),
                purchase.getValue()
        );
    }
}
