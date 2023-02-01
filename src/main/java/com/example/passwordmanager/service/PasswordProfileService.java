package com.example.passwordmanager.service;

import com.example.passwordmanager.model.PasswordProfile;
import com.example.passwordmanager.repository.PasswordProfileRepository;
import com.example.passwordmanager.repository.UserRepository;
import com.example.passwordmanager.response.AuthenticationResponse;
import com.example.passwordmanager.response.PasswordResponse;
import org.hibernate.internal.util.MutableBoolean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PasswordProfileService {

    private final PasswordProfileRepository passwordProfileRepository;
    private static final String SECRET_KEY = "367639792442264528482B4D6251655468576D5A7134743777217A25432A462D";

    public PasswordProfileService(PasswordProfileRepository passwordProfileRepository) {
        this.passwordProfileRepository = passwordProfileRepository;
    }

    // TODO: return passwordProfile response
    public List<PasswordProfile> getAllPasswords(String userId) {
        List<PasswordProfile> passwordProfileList = passwordProfileRepository.findAllByUserId(userId);
        List<PasswordProfile> decodedPasswordProfileList = new ArrayList<>();
        for (PasswordProfile passwordProfile: passwordProfileList) {
            PasswordProfile decodedPasswordProfile = PasswordProfile.builder()
                    .id(passwordProfile.getId())
                    .category(passwordProfile.getCategory())
                    .appName(passwordProfile.getAppName())
                    .loginId(passwordProfile.getLoginId())
                    .password(AES.decrypt(passwordProfile.getPassword(), SECRET_KEY))
                    .version(passwordProfile.getVersion())
                    .userId(passwordProfile.getUserId())
                    .build();
            decodedPasswordProfileList.add(decodedPasswordProfile);
        }
        return decodedPasswordProfileList;
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
        PasswordProfile passwordProfileToBeCreated = PasswordProfile.builder()
                .category(newPasswordProfile.getCategory())
                .appName(newPasswordProfile.getAppName())
                .loginId(newPasswordProfile.getLoginId())
                .password(AES.encrypt(newPasswordProfile.getPassword(), SECRET_KEY))
                .version(newPasswordProfile.getVersion() != null ? newPasswordProfile.getVersion() : 1L)
                .userId(newPasswordProfile.getUserId())
                .build();
        passwordProfileRepository.save(passwordProfileToBeCreated);
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
