package com.goestoque.goestoqueservice.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping(path = "/create")
    public @ResponseBody String create(@RequestBody User user) {

        repository.save(user);
        return "Saved";
    }

    @GetMapping("/readall")
    public @ResponseBody Iterable<User> readAll() {
        return repository.findAll();
    }
}
