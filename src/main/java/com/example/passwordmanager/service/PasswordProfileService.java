package com.example.passwordmanager.service;

import com.example.passwordmanager.model.PasswordProfile;
import com.example.passwordmanager.repository.PasswordProfileRepository;
import com.example.passwordmanager.repository.UserRepository;
import org.hibernate.internal.util.MutableBoolean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PasswordProfileService {

    private final PasswordProfileRepository passwordProfileRepository;

    public PasswordProfileService(PasswordProfileRepository passwordProfileRepository) {
        this.passwordProfileRepository = passwordProfileRepository;
    }

    public List<PasswordProfile> getAllPasswords(String userId) {
        return passwordProfileRepository.findAllByUserId(userId);
    }

    public String createNewPassword (PasswordProfile newPasswordProfile) {
        ArrayList<PasswordProfile> passwordsOfSameApplication = passwordProfileRepository.findAllByAppNameAndUserId(newPasswordProfile.getAppName(), newPasswordProfile.getUserId());
        MutableBoolean duplicate = new MutableBoolean(false);
        
        for (PasswordProfile existingPassword: passwordsOfSameApplication) {
            if (existingPassword.getLoginId().equals(newPasswordProfile.getLoginId())) {
                duplicate.setValue(true);
                break;
            }
        }
        if (duplicate.getValue() == true) {
            throw new IllegalStateException("Cannot save passwords with duplicate loginId in the same application");
        }
        if (newPasswordProfile.getVersion() == null) {
            newPasswordProfile.setVersion(1L);
        }
        passwordProfileRepository.save(newPasswordProfile);
        return "Password created successfully";
    }

    public String editPassword (PasswordProfile passwordProfile) {
        boolean exists = passwordProfileRepository.existsById(passwordProfile.getId());
        if (!exists) {
            throw new IllegalStateException("Invalid password profile id");
        }
        Optional<PasswordProfile> originalPasswordProfile = passwordProfileRepository.findById(passwordProfile.getId());
        passwordProfile.setVersion(originalPasswordProfile.get().getVersion() + 1);
        passwordProfileRepository.save(passwordProfile);
        return "appName is " + passwordProfile.getAppName() +
                " loginId is " + passwordProfile.getLoginId() +
                " password is " + passwordProfile.getPassword() +
                " version is " + passwordProfile.getVersion();
    }

    public String deletePassword(Long id) {
        boolean exists = passwordProfileRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Invalid password profile id");
        }
        passwordProfileRepository.deleteById(id);
        return "Password deleted";
    }

    public String generatePassword() {
        return "success";
    }
}
