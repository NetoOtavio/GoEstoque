package com.goestoque.goestoqueservice.inputs;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "pit_purchase_items")
@Data
public class PurchaseItem {

    @Id
    private Long id;

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
