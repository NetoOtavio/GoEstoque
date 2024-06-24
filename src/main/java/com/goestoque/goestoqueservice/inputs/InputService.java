package com.goestoque.goestoqueservice.inputs;

import com.goestoque.goestoqueservice.exception.ItemNotFoundException;
import com.goestoque.goestoqueservice.items.Item;
import com.goestoque.goestoqueservice.items.ItemRepository;
import com.goestoque.goestoqueservice.items.ItemService;
import com.goestoque.goestoqueservice.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InputService {

    private final InputRepository inputRepository;
    private final InputItemRepository inputItemRepository;
    private final ItemService itemService;

    public Input createInput(Set<InputItemDTO> inputItemDTOS) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Input input = Input.builder()
                .user(user)
                .build();
        inputRepository.save(input);
        Set<InputItem> inputItems = createSeveralInputItems(inputItemDTOS, input);
        for(InputItem inputItem : inputItems) {
            itemService.updateItemAvailableQuantity(inputItem.getItem(), inputItem.getAmount());
        }
        System.out.println(input.getInputItems());
        return input;
    }

    private Set<InputItem> createSeveralInputItems(Set<InputItemDTO> inputItemDTOS, Input input) {
        Set<InputItem> items = new HashSet<>();
        for( InputItemDTO inputItemDTO : inputItemDTOS) {
            items.add(createInputItem(inputItemDTO, input));
        }
        return items;
    }

    private InputItem createInputItem(InputItemDTO inputItemDTO, Input input) {
        Item item = itemService.readItemByUserAndCode(inputItemDTO.code());
        InputItem inputItem = InputItem.builder()
                .amount(inputItemDTO.amount())
                .input(input)
                .item(item)
                .build();
        inputItemRepository.save(inputItem);
        return inputItem;
    }

    public Input readInputByUserAndCode(UUID inputId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return inputRepository.findByUserAndId(user, inputId).orElseThrow();
    }

    public InputDTO convertToDTO(Input input) {
        return new InputDTO(
                input.getId(),
                input.getDate()
        );
    }
}