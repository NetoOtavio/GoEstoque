package com.goestoque.goestoqueservice.sales;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaleItemRepository extends JpaRepository<SaleItem, String> {

    List<SaleItem> findBySale(Sale sale);
}
