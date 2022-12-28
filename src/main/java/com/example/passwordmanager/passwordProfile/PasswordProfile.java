package com.example.passwordmanager.passwordProfile;

import jakarta.persistence.*;

@Entity
@Table
public class PasswordProfile {
    @Id
    @SequenceGenerator(
            name = "password_profile_sequence",
            sequenceName = "password_profile_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "password_profile_sequence"
    )
    private Long id;
    private String appName;
    private String loginId;
    private String password;
    private Long version;
//    private LocalDate lastUpdateTime;

    public PasswordProfile(String appName, String loginId, String password, Long version) {
        this.appName = appName;
        this.loginId = loginId;
        this.password = password;
        this.version = version;
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

//    public LocalDate getLastUpdateTime() {
//        return lastUpdateTime;
//    }
//
//    public void setLastUpdateTime(LocalDate lastUpdateTime) {
//        this.lastUpdateTime = lastUpdateTime;
//    }
}
