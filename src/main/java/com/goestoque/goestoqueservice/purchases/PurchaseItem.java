package com.goestoque.goestoqueservice.purchases;

import com.goestoque.goestoqueservice.inputs.InputItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "pit_purchase_items")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItem {

    @Id
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
