package com.goestoque.goestoqueservice.purchases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, String> {
    @Query(
            value = "select pur.* from pur_purchases pur join inp_inputs inp on inp.inp_id = pur.inp_id where inp.use_id = :userId",
            nativeQuery = true
    )
    List<Purchase> findByUser(@Param("userId")String userId);

    @Query(
            value = "select pur.* from pur_purchases pur join inp_inputs inp on inp.inp_id = pur.inp_id where inp.use_id = :userId and pur.inp_id = :purchaseId",
            nativeQuery = true
    )
    Optional<Purchase> findByUserAndId(@Param("userId")String userId, @Param("purchaseId")String purchaseId);
}
