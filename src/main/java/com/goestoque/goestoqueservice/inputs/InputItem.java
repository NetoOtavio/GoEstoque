package com.goestoque.goestoqueservice.inputs;

import com.goestoque.goestoqueservice.items.Item;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@Entity(name = "iit_input_items")
public class InputItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "iit_id")
    private UUID id;

    @Column(name = "iit_amount",
            nullable = false)
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ite_id",
                nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inp_id",
                nullable = false)
    private Input input;
}
