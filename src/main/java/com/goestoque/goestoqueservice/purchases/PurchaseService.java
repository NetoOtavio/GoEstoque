package com.goestoque.goestoqueservice.purchases;

import com.goestoque.goestoqueservice.exception.PurchaseNotFoundException;
import com.goestoque.goestoqueservice.inputs.Input;
import com.goestoque.goestoqueservice.inputs.InputItem;
import com.goestoque.goestoqueservice.inputs.InputItemDTO;
import com.goestoque.goestoqueservice.inputs.InputService;
import com.goestoque.goestoqueservice.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final InputService inputService;

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
            PurchaseItem purchaseItem = PurchaseItem.builder()
                    .inputItem(inputItem)
                    .price(purchaseItemDTO.purchaseItemPrice())
                    .purchase(purchase)
                    .build();
            purchaseItemRepository.save(purchaseItem);
            purchase.setValue(purchase.getValue() + purchaseItem.getPrice());
        }
        return purchaseRepository.save(purchase);
    }

    public List<Purchase> readPurchasesByUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return purchaseRepository.findByUser(user.getId());
    }

    public Purchase readPurchaseByUserAndId(String purchaseId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return purchaseRepository.findByUserAndId(user.getId() , purchaseId).orElseThrow( () -> new PurchaseNotFoundException(purchaseId));
    }

    public List<PurchaseItem> readPurchaseItemsByPurchase(String purchaseId) {
        Purchase purchase = readPurchaseByUserAndId(purchaseId);
        return purchaseItemRepository.findByPurchase(purchase);
    }

    public Set<InputItemDTO> convertPurchaseToInput(Set<PurchaseItemDTO> purchaseItemDTOS) {
        return purchaseItemDTOS.stream()
                .map(this::convertPurchaseItemDTOToInputItemDTO)
                .collect(Collectors.toSet());
    }
    public InputItemDTO convertPurchaseItemDTOToInputItemDTO(PurchaseItemDTO purchaseItemDTO) {
        return new InputItemDTO(purchaseItemDTO.itemCode(), purchaseItemDTO.amount());
    }

    public List<PurchaseDTO> convertPurchaseListToPurchaseDTOList(List<Purchase> purchaseList) {
        return purchaseList.stream()
                .map(this::convertPurchaseToPurchaseDTO)
                .collect(Collectors.toList());
    }

    public PurchaseDTO convertPurchaseToPurchaseDTO(Purchase purchase) {
        return new PurchaseDTO(
                purchase.getId(),
                purchase.getValue()
        );
    }

    public List<PurchaseItemDTO> convertPurchaseItemListToPurchaseItemDTOList(List<PurchaseItem> purchaseItemList) {
        return purchaseItemList.stream()
                .map(this::convertPurchaseItemToPurchaseItemDTO)
                .collect(Collectors.toList());
    }

    public PurchaseItemDTO convertPurchaseItemToPurchaseItemDTO(PurchaseItem purchaseItem) {
        InputItem inputItem = purchaseItem.getInputItem();
        return new PurchaseItemDTO(
                inputItem.getItem().getCode(),
                inputItem.getAmount(),
                purchaseItem.getPrice()
        );
    }
}
