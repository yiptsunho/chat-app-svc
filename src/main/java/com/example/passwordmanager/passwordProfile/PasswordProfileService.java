package com.example.passwordmanager.passwordProfile;

import com.example.passwordmanager.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasswordProfileService {

    private final PasswordProfileRepository passwordProfileRepository;
    private final UserRepository userRepository;

    public PasswordProfileService(PasswordProfileRepository passwordProfileRepository, UserRepository userRepository) {
        this.passwordProfileRepository = passwordProfileRepository;
        this.userRepository = userRepository;
    }

    public List<PasswordProfile> getAllPasswords(String userId) {
        return passwordProfileRepository.findAllByUserId(userId);
    }

    public String createNewPassword (PasswordProfile passwordProfile) {
        passwordProfileRepository.save(passwordProfile);
        return "appName is " + passwordProfile.getAppName() +
                " loginId is " + passwordProfile.getLoginId() +
                " password is " + passwordProfile.getPassword() +
                " version is " + passwordProfile.getVersion();
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

//    public String deletePassword(Long id) {
//        boolean exists = passwordProfileRepository.existsById(id);
//        if (!exists) {
//            throw new IllegalStateException("Invalid password profile id");
//        }
//        Optional<PasswordProfile> passwordToDelete = passwordProfileRepository.findById(id);
//        passwordProfileRepository.delete(passwordToDelete);
//        return "Password deleted";
//    }

//    public String generatePassword() {
//        return "success";
//    }
}
