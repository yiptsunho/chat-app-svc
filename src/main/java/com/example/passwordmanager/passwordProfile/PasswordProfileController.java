package com.example.passwordmanager.passwordProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PasswordProfileController {

    private PasswordProfile passwordProfile;

    private PasswordProfileService passwordProfileService;

    @Autowired
    private void PasswordController(PasswordProfile passwordProfile, PasswordProfileService passwordProfileService) {
        this.passwordProfile = passwordProfile;
        this.passwordProfileService = passwordProfileService;
    }

    @GetMapping("/api/getAllPasswords")
    private List<PasswordProfile> getAllPasswords(@RequestParam String userId) {
        return passwordProfileService.getAllPasswords(userId);
    }

    @PostMapping("/api/createNewPassword")
    private String createNewPassword (@RequestBody PasswordProfile passwordProfile) {
        return passwordProfileService.createNewPassword(passwordProfile);
    }

}
