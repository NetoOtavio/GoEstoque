package com.goestoque.goestoqueservice.items;

import com.goestoque.goestoqueservice.exception.ItemAlreadyExistsException;
import com.goestoque.goestoqueservice.exception.ItemNotFoundException;
import com.goestoque.goestoqueservice.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    public Item createItem(ItemDTO dto) {
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
        return item;
    }

    public List<Item> readAllItems() {
        return repository.findAll();
    }

    public List<Item> readItemsByUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.findByUser(user);
    }

    public Item readItemByUserAndCode(String itemCode) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.findByUserAndCode(user, itemCode).orElseThrow( () -> new ItemNotFoundException(itemCode));
    }

    public List<Item> readItemByUserAndName(String name) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getId());
        System.out.println(name);
        return repository.findByUserAndNameContaining(user.getId(), "%" + name + "%");
    }

    public ItemDTO convertToDTO(Item item) {
        return new ItemDTO(
                item.getCode(),
                item.getName(),
                item.getAvailableQuantity(),
                item.getPrice(),
                item.getDescription()
        );
    }

    public List<ItemDTO> convertToDTOList(List<Item> itemList) {
        return itemList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public  void updateItemAvailableQuantity(Item item, int value) {
        item.setAvailableQuantity(item.getAvailableQuantity() + value);
        repository.save(item);
    }
}
