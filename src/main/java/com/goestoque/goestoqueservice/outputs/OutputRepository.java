package com.goestoque.goestoqueservice.outputs;

import com.goestoque.goestoqueservice.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OutputRepository extends JpaRepository<Output, String> {
    List<Output> findByUser(User user);

    Optional<Output> findByUserAndId(User user, String outputId);
}
