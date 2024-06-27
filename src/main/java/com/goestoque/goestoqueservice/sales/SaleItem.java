package com.goestoque.goestoqueservice.sales;

import com.goestoque.goestoqueservice.outputs.OutputItem;
import com.goestoque.goestoqueservice.sales.Sale;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "sit_sale_items")
@Data
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "oit_id")
    private OutputItem outputItem;

    @Column(name = "sit_price",
            nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "out_id",
                nullable = false)
    private Sale sale;
}
