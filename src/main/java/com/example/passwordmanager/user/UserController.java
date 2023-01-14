package com.example.passwordmanager.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private User user;

    private UserService userService;

    @Autowired
    private void UserController(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
    }


    @GetMapping("/")
    public String getGreeting() {
        return "Welcome to password manager";
    }

    @PostMapping("/api/login")
    public User login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/api/createNewUser")
    public void createNewUser(@RequestBody User user) {
        userService.createNewUser(user);
    }

    @PostMapping("/api/editUser")
    public String editUser(@RequestBody User user) {
        return userService.editUser(user);
    }

}
