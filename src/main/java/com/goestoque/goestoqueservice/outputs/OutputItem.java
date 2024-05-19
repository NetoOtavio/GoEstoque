package com.goestoque.goestoqueservice.outputs;

import com.goestoque.goestoqueservice.items.Item;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "oit_output_items")
@Data
public class OutputItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oit_id")
    private long id;

    @Column(name = "oit_amount",
            nullable = false)
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ite_id",
                nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "out_id",
                nullable = false)
    private Output output;
}
