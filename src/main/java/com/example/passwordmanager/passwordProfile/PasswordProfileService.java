package com.example.passwordmanager.passwordProfile;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordProfileService {

    private final PasswordProfileRepository passwordProfileRepository;

    public PasswordProfileService(PasswordProfileRepository passwordProfileRepository) {
        this.passwordProfileRepository = passwordProfileRepository;
    }

    public List<PasswordProfile> getAllPasswords(String userId) {
        return passwordProfileRepository.findAll();
    }

    public String createNewPassword (PasswordProfile passwordProfile) {
        return "appName is " + passwordProfile.getAppName() +
                " loginId is " + passwordProfile.getLoginId() +
                " password is " + passwordProfile.getPassword() +
                " version is " + passwordProfile.getVersion();
    }

}
