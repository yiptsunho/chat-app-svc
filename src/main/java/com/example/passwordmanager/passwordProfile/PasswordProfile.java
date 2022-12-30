package com.example.passwordmanager.passwordProfile;

import com.example.passwordmanager.user.User;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table
@Component
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
    private String category;
    private String appName;
    private String loginId;
    private String password;
    private Long version;
    private Long userId;
//    private LocalDate lastUpdateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;


    public PasswordProfile(Long id, String category, String appName, String loginId, String password, Long version, Long userId) {
        this.id = id;
        this.category = category;
        this.appName = appName;
        this.loginId = loginId;
        this.password = password;
        this.version = version;
        this.userId = userId;
    }

    public PasswordProfile() {
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    //    public LocalDate getLastUpdateTime() {
//        return lastUpdateTime;
//    }
//
//    public void setLastUpdateTime(LocalDate lastUpdateTime) {
//        this.lastUpdateTime = lastUpdateTime;
//    }
}
