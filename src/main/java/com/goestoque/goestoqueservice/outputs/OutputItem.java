package com.goestoque.goestoqueservice.outputs;

import com.goestoque.goestoqueservice.items.Item;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity(name = "oit_output_items")
@Data
public class OutputItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "oit_id")
    private UUID id;

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
