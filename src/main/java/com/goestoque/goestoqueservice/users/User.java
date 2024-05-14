package com.goestoque.goestoqueservice.user;

import jakarta.persistence.*;

@Entity(name = "use_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "use_name")
    private Integer id;

    private String name;

    private String email;
}
