package com.goestoque.goestoqueservice.users;

import jakarta.persistence.*;
import lombok.Data;

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
}
