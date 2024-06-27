package com.goestoque.goestoqueservice.inputs;

import com.goestoque.goestoqueservice.exception.InputItemNotFoundException;
import com.goestoque.goestoqueservice.exception.InputNotFoundException;
import com.goestoque.goestoqueservice.items.Item;
import com.goestoque.goestoqueservice.items.ItemService;
import com.goestoque.goestoqueservice.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        return input;
    }

    private Set<InputItem> createSeveralInputItems(Set<InputItemDTO> inputItemDTOS, Input input) {
        Set<InputItem> inputItems = new HashSet<>();
        for( InputItemDTO inputItemDTO : inputItemDTOS) {
            inputItems.add(createInputItem(inputItemDTO, input));
        }
        return inputItems;
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

    public List<Input> readInputsByUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return inputRepository.findByUser(user);
    }

    public  Input readInputByUserAndId(String inputId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return inputRepository.findByUserAndId(user, inputId).orElseThrow( () -> new InputNotFoundException(inputId));
    }

    public List<InputItem> readInputItemsByInputId(String inputId) {
        Input input = readInputByUserAndId(inputId);
        return inputItemRepository.findByInput(input);
    }

    public InputItem readInputItemByInputIdAndItemCode(String inputId, String itemCode) {
        return inputItemRepository.findByInputAndItemCode(inputId, itemCode).orElseThrow( () -> new InputItemNotFoundException("teste") );
    }

    /*
    public InputItem readInputItemByInputAndId(String purchaseId, String inputItemId) {
        Input input = readInputByUserAndId(purchaseId);
        return inputItemRepository.findByInputAndId(input, inputItemId).orElseThrow( () -> new InputItemNotFoundException("teste"));
    }
    */

    public InputDTO convertToInputDTO(Input input) {
        return new InputDTO(
                input.getId(),
                input.getDate()
        );
    }

    public List<InputDTO> convertToInputDTOList(List<Input> inputList) {
        return inputList.stream()
                .map(this::convertToInputDTO)
                .collect(Collectors.toList());
    }

    public InputItemDTO convertToInputItemDTO(InputItem inputItem) {
        return new InputItemDTO(
                inputItem.getItem().getCode(),
                inputItem.getAmount()
        );
    }

    public List<InputItemDTO> convertToInputItemDTOList(List<InputItem> inputItemList) {
        return inputItemList.stream()
                .map(this::convertToInputItemDTO)
                .collect(Collectors.toList());
    }
}