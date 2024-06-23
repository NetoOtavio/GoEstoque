package com.goestoque.goestoqueservice.inputs;

import com.goestoque.goestoqueservice.items.Item;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity(name = "iit_input_items")
public class InputItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iit_id")
    private Long id;

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
