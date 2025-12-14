package com.banking.banking_app.controller;

import com.banking.banking_app.dto.UserDTO;
import com.banking.banking_app.model.User;
import com.banking.banking_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody @Valid UserDTO req) {
        return userService.createUser(req.getUsername(), req.getEmail());

    }

}
