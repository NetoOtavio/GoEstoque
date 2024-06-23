package com.goestoque.goestoqueservice.inputs;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity(name = "pur_purchase")
@Data
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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
