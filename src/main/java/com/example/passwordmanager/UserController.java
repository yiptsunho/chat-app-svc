package com.example.passwordmanager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public String hellowWorld() {
        return "Welcome to password manager";
    }

}
