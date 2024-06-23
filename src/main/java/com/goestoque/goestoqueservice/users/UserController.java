package com.goestoque.goestoqueservice.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/readall")
    public ResponseEntity<List<User>> readAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.readAllUsers());
    }
}
