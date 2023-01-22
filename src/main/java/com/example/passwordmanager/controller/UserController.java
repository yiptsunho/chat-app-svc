package com.example.passwordmanager.controller;

import com.example.passwordmanager.model.User;
import com.example.passwordmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    @Autowired
    private void UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String getGreeting() {
        return "Welcome to password manager";
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping()
    public void createNewUser(@RequestBody User user) {
        userService.createNewUser(user);
    }

    @PutMapping()
    public String editUser(@RequestBody User user) {
        return userService.editUser(user);
    }

}
