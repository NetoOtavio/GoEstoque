package com.goestoque.goestoqueservice.items;

import com.goestoque.goestoqueservice.users.User;
import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@Entity(name = "ite_items")
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ite_id")
    private Long id;

    @Column(name = "ite_codigo",
            nullable = false,
            unique = true)
    private String code;

    @Column(name = "ite_name",
            nullable = false)
    private String name;

    @Column(name = "ite_available_quantity", nullable = false)
    private int availableQuantity;

    @Column(name = "ite_price",
            nullable = false)
    private double price;

    @Column(name = "ite_description")
    private String description;

    @Column(name = "ite_is_active", nullable = false)
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "use_id",
                nullable = false,
                updatable = false)
    private User user;
}
