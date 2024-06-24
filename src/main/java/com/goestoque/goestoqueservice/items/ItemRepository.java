package com.goestoque.goestoqueservice.items;

import com.goestoque.goestoqueservice.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, String> {
    List<Item> findByNameContaining(String name);
    List<Item> findByUser(User user);
    Optional<Item> findByUserAndCode(User user, String code);
}
