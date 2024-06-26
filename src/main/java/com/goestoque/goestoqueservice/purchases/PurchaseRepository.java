package com.goestoque.goestoqueservice.purchases;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, String> {
    @Query(value = "select pur.* from pur_purchases pur join inp_inputs inp on inp.inp_id = pur.inp_id where inp.use_id = :inputUserId", nativeQuery = true)
    List<Purchase> findByInputUserId(@Param("inputUserId")String inputUserId);
}
