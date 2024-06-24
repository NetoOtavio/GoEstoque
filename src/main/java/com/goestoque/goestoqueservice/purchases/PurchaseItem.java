package com.goestoque.goestoqueservice.purchases;

import com.goestoque.goestoqueservice.inputs.InputItem;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Entity(name = "pit_purchase_items")
@Data
@Builder
public class PurchaseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "iit_id")
    private InputItem inputItem;

    @Column(name = "pit_price",
            nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inp_id",
                nullable = false)
    private Purchase purchase;
}
