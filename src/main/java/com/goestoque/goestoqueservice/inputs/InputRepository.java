package com.goestoque.goestoqueservice.inputs;

import com.goestoque.goestoqueservice.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface InputRepository extends JpaRepository<Input, UUID> {

    public Optional<Input> findByUserAndId(User user, UUID inputId );
}
