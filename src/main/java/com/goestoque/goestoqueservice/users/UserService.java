package com.goestoque.goestoqueservice.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> readAllUsers() {
        return repository.findAll();
    }
}
