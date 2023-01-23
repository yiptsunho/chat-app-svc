package com.example.passwordmanager.controller;

import com.example.passwordmanager.model.PasswordProfile;
import com.example.passwordmanager.response.PasswordResponse;
import com.example.passwordmanager.service.PasswordProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/passwordProfile")
public class PasswordProfileController {

    private PasswordProfileService passwordProfileService;

    @Autowired
    private void PasswordController(PasswordProfileService passwordProfileService) {
        this.passwordProfileService = passwordProfileService;
    }

    @GetMapping()
    private List<PasswordProfile> getAllPasswords(@RequestParam String userId) {
        return passwordProfileService.getAllPasswords(userId);
    }

    @PostMapping()
    private String createNewPassword (@RequestBody PasswordProfile passwordProfile) {
        return passwordProfileService.createNewPassword(passwordProfile);
    }

    @PutMapping()
    private String editPassword (@RequestBody PasswordProfile passwordProfile) {
        return passwordProfileService.editPassword(passwordProfile);
    }

    @DeleteMapping()
    private String deletePassword (@RequestParam Long id) {
        return passwordProfileService.deletePassword(id);
    }
}