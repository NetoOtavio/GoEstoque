package com.goestoque.goestoqueservice.purchases;

import com.goestoque.goestoqueservice.inputs.Input;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity(name = "pur_purchases")
@Data
@Builder
public class Purchase {

    @Id
    private String id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "inp_id")
    private Input input;

    @Column(name = "pur_value",
            nullable = false)
    private double value;

    @OneToMany(mappedBy = "purchase")
    private Set<PurchaseItem> purchaseItems;
}
