package com.goestoque.goestoqueservice.items;

import com.goestoque.goestoqueservice.exception.ItemAlreadyExistsException;
import com.goestoque.goestoqueservice.exception.ItemNotFoundException;
import com.goestoque.goestoqueservice.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    public ItemDTO createItem(ItemDTO dto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(repository.findByUserAndCode(user, dto.code()).isPresent()) throw new ItemAlreadyExistsException(dto.code());
        Item item = Item.builder()
                .code(dto.code())
                .name(dto.name())
                .availableQuantity(0)
                .price(dto.price())
                .description(dto.description())
                .user(user)
                .isActive(true)
                .build();
        repository.save(item);
        return convertToDTO(item);
    }

    public List<ItemDTO> readAllItems() {
        List<Item> items = repository.findAll();
        return items.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ItemDTO> readItemsByUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Item> items = repository.findByUser(user);
        return items.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ItemDTO readItemByUserAndCode(String itemCode) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Item item = repository.findByUserAndCode(user, itemCode).orElseThrow( () -> new ItemNotFoundException(itemCode));
        return convertToDTO(item);
    }

    private ItemDTO convertToDTO(Item item) {
        return new ItemDTO(
                item.getCode(),
                item.getName(),
                item.getPrice(),
                item.getDescription()
        );
    }
}
