package com.goestoque.goestoqueservice.outputs;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity(name = "sal_sales")
@Data
public class Sale {

    @Id
    private Long id;

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
