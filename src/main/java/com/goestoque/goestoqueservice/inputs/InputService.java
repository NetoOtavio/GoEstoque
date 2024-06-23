package com.goestoque.goestoqueservice.inputs;

import com.goestoque.goestoqueservice.exception.ItemNotFoundException;
import com.goestoque.goestoqueservice.items.Item;
import com.goestoque.goestoqueservice.items.ItemRepository;
import com.goestoque.goestoqueservice.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class InputService {

    private final InputRepository inputRepository;
    private final InputItemRepository inputItemRepository;
    private final ItemRepository itemRepository;

    public InputDTO createInput(Set<InputItemDTO> inputItemDTOS) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Input input = Input.builder()
                .user(user)
                .build();
        inputRepository.save(input);
        createSeveralInputItems(inputItemDTOS, user, input);
        return new InputDTO(input.getId(), "successfully created");
    }

    private Set<InputItem> createSeveralInputItems(Set<InputItemDTO> inputItemDTOS, User user, Input input) {
        Set<InputItem> items = new HashSet<>();
        for( InputItemDTO inputItemDTO : inputItemDTOS) {
            items.add(createInputItem(inputItemDTO, user, input));
        }
        return items;
    }

    private InputItem createInputItem(InputItemDTO inputItemDTO, User user, Input input) {

        Item item = itemRepository.findByUserAndCode(user, inputItemDTO.code()).orElseThrow(() -> new ItemNotFoundException(inputItemDTO.code()));
        InputItem inputItem = InputItem.builder()
                .amount(inputItemDTO.amount())
                .input(input)
                .item(item)
                .build();
        inputItemRepository.save(inputItem);
        return inputItem;
    }
}