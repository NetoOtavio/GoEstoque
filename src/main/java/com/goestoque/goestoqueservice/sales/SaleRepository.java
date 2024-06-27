package com.goestoque.goestoqueservice.sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, String> {

    @Query(
            value = "select sal.* from sal_sales sal join out_outputs otp on otp.out_id = sal.out_id where otp.use_id = :userId",
            nativeQuery = true
    )
    List<Sale> findByUser(@Param("userId")String userId);

    @Query(
            value = "select sal.* from sal_sales sal join out_outputs otp on otp.out_id = sal.out_id where otp.use_id = :userId and sal.out_id = :saleId",
            nativeQuery = true
    )
    Optional<Sale> findByUserAndId(@Param("userId")String userId, @Param("saleId")String saleId);
}
