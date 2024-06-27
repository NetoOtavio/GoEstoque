package com.goestoque.goestoqueservice.outputs;

import com.goestoque.goestoqueservice.exception.OutputNotFoundException;
import com.goestoque.goestoqueservice.items.Item;
import com.goestoque.goestoqueservice.items.ItemService;
import com.goestoque.goestoqueservice.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OutputService {

    private final OutputRepository outputRepository;
    private final OutputItemRepository outputItemRepository;
    private final ItemService itemService;

    Output createOutput(Set<OutputItemDTO> outputItemDTOS) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Output output = Output.builder()
                .user(user)
                .build();
        outputRepository.save(output);
        for(OutputItemDTO outputItemDTO : outputItemDTOS) {
            Item item = itemService.readItemByUserAndCode(outputItemDTO.code());
            OutputItem outputItem = OutputItem.builder()
                    .amount(outputItemDTO.amount())
                    .output(output)
                    .item(item)
                    .build();
            outputItemRepository.save(outputItem);
            itemService.updateItemAvailableQuantity(item, outputItem.getAmount() * -1);
        }
        return output;
    }

    public List<Output> readOutputsByUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return outputRepository.findByUser(user);
    }

    public Output readOutputByUserAndId(String outputId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return outputRepository.findByUserAndId(user, outputId).orElseThrow(() -> new OutputNotFoundException(outputId));
    }

    public List<OutputItem> readOutputItemsByOutputId(String outputId) {
        Output output = readOutputByUserAndId(outputId);
        return outputItemRepository.findByOutput(output);
    }

    public OutputDTO convertOutputToOutputDTO(Output output) {
        return new OutputDTO(output.getId(), output.getDate());
    }

    public List<OutputDTO> convertOutputListToOutputDTOList(List<Output> outputs) {
        return outputs.stream()
                .map(this::convertOutputToOutputDTO)
                .collect(Collectors.toList());
    }

    public OutputItemDTO convertOutputItemToOutputItemDTO(OutputItem outputItem) {
        return new OutputItemDTO(
                outputItem.getItem().getCode(),
                outputItem.getAmount()
        );
    }

    public List<OutputItemDTO> convertOutputItemListToOutputItemDTOList(List<OutputItem> outputItems) {
        return outputItems.stream()
                .map(this::convertOutputItemToOutputItemDTO)
                .collect(Collectors.toList());
    }
}
