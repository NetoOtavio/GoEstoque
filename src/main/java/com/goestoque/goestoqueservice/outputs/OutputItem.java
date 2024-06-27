package com.goestoque.goestoqueservice.outputs;

import com.goestoque.goestoqueservice.items.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "oit_output_items")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutputItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "oit_id")
    private String id;

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
