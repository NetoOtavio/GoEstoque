package com.goestoque.goestoqueservice.purchases;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, String> {
}
