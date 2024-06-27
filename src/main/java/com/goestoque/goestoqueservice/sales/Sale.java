package com.goestoque.goestoqueservice.sales;

import com.goestoque.goestoqueservice.outputs.Output;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity(name = "sal_sales")
@Data
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

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
