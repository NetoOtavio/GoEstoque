package com.goestoque.goestoqueservice.outputs;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity(name = "sal_sales")
@Data
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "out_id")
    private Output output;

    @Column(name = "sal_value",
            nullable = false)
    private double value;

    @OneToMany(mappedBy = "sale")
    private Set<SaleItem> saleItems;
}
