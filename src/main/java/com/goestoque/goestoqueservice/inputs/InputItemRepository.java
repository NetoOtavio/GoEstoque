package com.goestoque.goestoqueservice.inputs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InputItemRepository extends JpaRepository<InputItem, String> {
    List<InputItem> findByInput(Input input);

    /*
    Optional<InputItem> findByInputAndId(Input input, String id);
    */

    @Query(value = "select iit.* from iit_input_items iit join ite_items ite on ite.ite_id = iit.ite_id where iit.inp_id = :purchaseId and ite.ite_code = :itemCode", nativeQuery = true)
    Optional<InputItem> findByInputAndItemCode(@Param("purchaseId")String inputId, @Param("itemCode")String itemCode);
}
