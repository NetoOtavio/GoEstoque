package com.goestoque.goestoqueservice.outputs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutputItemRepository extends JpaRepository<OutputItem, String> {
    List<OutputItem> findByOutput(Output output);
}
