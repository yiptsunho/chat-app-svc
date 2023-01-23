package com.example.passwordmanager.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResponse {

    private Long id;
    private String category;
    private String appName;
    private String loginId;
    private String password;
    private Long version;
    private Long userId;
}
