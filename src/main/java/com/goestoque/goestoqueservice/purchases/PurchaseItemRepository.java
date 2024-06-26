package com.goestoque.goestoqueservice.purchases;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, String> {

    List<PurchaseItem> findByPurchase(Purchase purchase);
}
