package com.example.passwordmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordProfile {
    @Id
    @SequenceGenerator(
            name = "password_profile_sequence",
            sequenceName = "password_profile_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "password_profile_sequence"
    )
    private Long id;
    @NotNull("category must not be empty")
    private String category;
    @NotNull("appName must not be empty")
    private String appName;
    @NotNull("loginId must not be empty")
    private String loginId;
    @NotNull("password must not be empty")
    private String password;
    private Long version;
    @NotNull("userId must not be empty")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;
}
