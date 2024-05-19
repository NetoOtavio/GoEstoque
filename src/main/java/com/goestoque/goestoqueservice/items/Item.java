package com.goestoque.goestoqueservice.items;

import com.goestoque.goestoqueservice.users.User;
import jakarta.persistence.*;

@Entity(name = "ite_items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ite_id")
    private Long id;

    @Column(name = "ite_name",
            nullable = false)
    private String name;

    @Column(name = "ite_available_quantity",
            nullable = false)
    private int availableQuantity;

    @Column(name = "ite_price",
            nullable = false)
    private double price;

    @Column(name = "ite_description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "use_id",
                nullable = false)
    private User user;
}
