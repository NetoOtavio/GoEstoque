package com.goestoque.goestoqueservice.outputs;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "sit_sale_items")
@Data
public class SaleItem {

    @Id
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "oit_id")
    private OutputItem outputItem;

    @Column(name = "sit_price",
            nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sal_id",
                nullable = false)
    private Sale sale;
}
