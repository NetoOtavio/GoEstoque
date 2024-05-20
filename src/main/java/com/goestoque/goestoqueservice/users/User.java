package com.goestoque.goestoqueservice.users;

import com.goestoque.goestoqueservice.items.Item;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity(name = "use_users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "use_id")
    private Long id;

    @Column(name = "use_email",
            nullable = false,
            unique = true)
    private String email;

    @Column(name = "use_name",
            nullable = false)
    private String name;

    @OneToMany(mappedBy = "user")
    private Set<Item> items;
}
