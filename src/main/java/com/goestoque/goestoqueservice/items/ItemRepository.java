package com.goestoque.goestoqueservice.items;

import com.goestoque.goestoqueservice.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, String> {
    @Query(
            value = "select * from ite_items where ite_name like :name and use_id = :userId",
            nativeQuery = true
    )
    List<Item> findByUserAndNameContaining(@Param("userId")String userId, @Param("name")String name);
    List<Item> findByUser(User user);
    Optional<Item> findByUserAndCode(User user, String code);
}
