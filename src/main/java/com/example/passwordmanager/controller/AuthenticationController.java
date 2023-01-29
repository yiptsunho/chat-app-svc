package com.example.passwordmanager.controller;

import com.example.passwordmanager.request.AuthenticationRequest;
import com.example.passwordmanager.request.RefreshRequest;
import com.example.passwordmanager.request.RegisterRequest;
import com.example.passwordmanager.response.AuthenticationResponse;
import com.example.passwordmanager.response.RefreshResponse;
import com.example.passwordmanager.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    private void AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/createNewUser")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<RefreshResponse> register(
            @RequestBody RefreshRequest request
    ) {
        return ResponseEntity.ok(authenticationService.refresh(request));
    }

}
