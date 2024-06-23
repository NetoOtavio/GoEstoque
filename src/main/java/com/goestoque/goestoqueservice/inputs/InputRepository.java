package com.goestoque.goestoqueservice.inputs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InputRepository extends JpaRepository<Input, UUID> {
}
