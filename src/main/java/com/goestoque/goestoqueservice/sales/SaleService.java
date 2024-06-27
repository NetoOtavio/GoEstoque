package com.goestoque.goestoqueservice.sales;

import com.goestoque.goestoqueservice.exception.SaleNotFoundException;
import com.goestoque.goestoqueservice.outputs.Output;
import com.goestoque.goestoqueservice.outputs.OutputItem;
import com.goestoque.goestoqueservice.outputs.OutputItemDTO;
import com.goestoque.goestoqueservice.outputs.OutputService;
import com.goestoque.goestoqueservice.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final OutputService outputService;

    public Sale createSale(Set<SaleItemDTO> saleItemDTOS) {
        Set<OutputItemDTO> outputItemDTOS = convertSaleToOutput(saleItemDTOS);
        Output output = outputService.createOutput(outputItemDTOS);
        Sale sale = Sale.builder()
                .output(output)
                .value(0)
                .build();
        saleRepository.save(sale);
        for(SaleItemDTO saleItemDTO : saleItemDTOS) {
            OutputItem outputItem = outputService.readOutputItemByOutputIdAndItemCode(output.getId(), saleItemDTO.itemCode());
            SaleItem saleItem = SaleItem.builder()
                    .outputItem(outputItem)
                    .price(outputItem.getItem().getPrice() * outputItem.getAmount())
                    .sale(sale)
                    .build();
            saleItemRepository.save(saleItem);
            sale.setValue(sale.getValue() + saleItem.getPrice());
        }
        return saleRepository.save(sale);
    }

    public List<Sale> readSalesByUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return saleRepository.findByUser(user.getId());
    }

    public Sale readSaleByUserAndId(String saleId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return saleRepository.findByUserAndId(user.getId(), saleId).orElseThrow( () -> new SaleNotFoundException(saleId));
    }

    public List<SaleItem> readSaleItemsBySale(String saleId) {
        Sale sale = readSaleByUserAndId(saleId);
        return saleItemRepository.findBySale(sale);
    }

    public Set<OutputItemDTO> convertSaleToOutput(Set<SaleItemDTO> saleItemDTOS) {
        return saleItemDTOS.stream()
                .map(this::convertSaleItemDTOToOutputItemDTO)
                .collect(Collectors.toSet());
    }

    public OutputItemDTO convertSaleItemDTOToOutputItemDTO(SaleItemDTO saleItemDTO) {
        return new OutputItemDTO(saleItemDTO.itemCode(), saleItemDTO.amount());
    }

    public List<SaleDTO> convertSaleListToSaleDTOList(List<Sale> saleList) {
        return saleList.stream()
                .map(this::convertSaleToSaleDTO)
                .collect(Collectors.toList());
    }

    public SaleDTO convertSaleToSaleDTO(Sale sale) {
        return new SaleDTO(
                sale.getId(),
                sale.getValue()
        );
    }

    public List<SaleItemDTO> convertSaleItemListToSaleItemDTOList(List<SaleItem> saleItemList) {
        return saleItemList.stream()
                .map(this::convertSaleItemToSaleItemDTO)
                .collect(Collectors.toList());
    }

    public SaleItemDTO convertSaleItemToSaleItemDTO(SaleItem saleItem) {
        OutputItem outputItem = saleItem.getOutputItem();
        return new SaleItemDTO(
                outputItem.getItem().getCode(),
                outputItem.getAmount(),
                saleItem.getPrice()
        );
    }
}
