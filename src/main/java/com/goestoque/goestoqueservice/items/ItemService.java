package com.goestoque.goestoqueservice.items;

import com.goestoque.goestoqueservice.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    public CreateResponseDTO createItem(CreateRequestDTO dto) {

        User userPrincipal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var item = Item.builder()
                .code(dto.code())
                .name(dto.name())
                .availableQuantity(dto.availableQuantity())
                .price(dto.price())
                .description(dto.description())
                .user(userPrincipal)
                .isActive(true)
                .build();
        repository.save(item);
        return new CreateResponseDTO(item.getName(), "successfully created");
    }

    public List<ItemResponseDTO> findAllItems() {
        List<Item> items = repository.findAll();
        return items.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ItemResponseDTO> readItemsByUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Item> items = repository.findByUser(user);
        return items.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ItemResponseDTO convertToDTO(Item item) {
        return new ItemResponseDTO(item.getId(), item.getName(), item.getAvailableQuantity(), item.getPrice(), item.getDescription());
    }
}
