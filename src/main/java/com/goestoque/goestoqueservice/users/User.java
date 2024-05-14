package com.goestoque.goestoqueservice.users;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "use_users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "use_name")
    private Integer id;

    private String name;

    private String email;
}
