package com.example.passwordmanager.service;

import com.example.passwordmanager.model.User;
import com.example.passwordmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(User user) {
        boolean exists = userRepository.existsByLoginId(user.getLoginId());
        if (!exists) {
            throw new IllegalStateException("email address or password incorrect");
        }
        User targetUser = userRepository.findByLoginId(user.getLoginId());
        String password = targetUser.getPassword();
        boolean correct = password.equals(user.getPassword());
        if (!correct) {
            throw new IllegalStateException("email address or password incorrect");
        }

        targetUser.setLoginId(null);
        targetUser.setPassword(null);
        return targetUser;
    }

    public String createNewUser(User user) {
        boolean exists = userRepository.existsByLoginId(user.getLoginId());
        if (exists) {
            throw new IllegalStateException("This email address has been registered");
        }
        userRepository.save(user);
        return "success";
//        return "userName: " + user.getUserName() + " password: " + user.getPassword();
    }

    public String editUser(User user) {
        boolean exists = userRepository.existsByLoginId(user.getLoginId());
        if (exists) {
            throw new IllegalStateException("User does not exist in the system");
        }
        userRepository.save(user);
        return "userName: " + user.getLoginId() + " password: " + user.getPassword();
    }

}
