package com.goestoque.goestoqueservice.inputs;

import com.goestoque.goestoqueservice.items.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity(name = "iit_input_items")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InputItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "iit_id")
    private String id;

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
