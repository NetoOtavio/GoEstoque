package com.goestoque.goestoqueservice.outputs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OutputItemRepository extends JpaRepository<OutputItem, String> {
    List<OutputItem> findByOutput(Output output);

    @Query(value = "select oit.* from oit_output_items oit join ite_items ite on ite.ite_id = oit.ite_id where oit.out_id = :outputId and ite.ite_code = :itemCode", nativeQuery = true)
    Optional<OutputItem> findByOutputAndItemCode(@Param("outputId")String outputId, @Param("itemCode")String itemCode);
}
