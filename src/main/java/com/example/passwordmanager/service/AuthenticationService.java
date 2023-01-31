package com.example.passwordmanager.service;

import com.example.passwordmanager.model.Role;
import com.example.passwordmanager.model.User;
import com.example.passwordmanager.repository.UserRepository;
import com.example.passwordmanager.request.AuthenticationRequest;
import com.example.passwordmanager.request.RefreshRequest;
import com.example.passwordmanager.request.RegisterRequest;
import com.example.passwordmanager.response.AuthenticationResponse;
import com.example.passwordmanager.response.RefreshResponse;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .loginId(request.getLoginId())
                .password(passwordEncoder.encode(request.getPassword()))
                .displayName(request.getDisplayName())
                .role(Role.USER)
                .build();
        boolean exists = userRepository.existsByLoginId(user.getLoginId());
        if (exists) {
            throw new IllegalStateException("User already exist in the system");
        }
        userRepository.save(user);
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .displayName(user.getDisplayName())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLoginId(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByLoginId(request.getLoginId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .displayName(user.getDisplayName())
                .build();
    }
    // TODO: should not include loginId and password in the payload, BE shd find the user in the token header
    public RefreshResponse refresh(HttpServletRequest request) {
//        String authorizationHeader = request.getHeader("AUTHORIZATION");
//        String token = authorizationHeader.substring("Bearer ".length());
//        Algorithm
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getLoginId(),
//                        request.getPassword()
//                )
//        );
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String loginId;
        jwt = authHeader.substring(7);
        loginId = jwtService.extractLoginId(jwt);
        var user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return RefreshResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
