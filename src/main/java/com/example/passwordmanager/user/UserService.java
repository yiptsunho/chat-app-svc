package com.example.passwordmanager.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(User user) {
        boolean exists = userRepository.existsByLoginId(user.getLoginId());
        if (!exists) {
            throw new IllegalStateException("email address or password incorrect");
        }
        String password = userRepository.findByLoginId(user.getLoginId()).getPassword();
        boolean correct = password.equals(user.getPassword());
        if (!correct) {
            throw new IllegalStateException("email address or password incorrect");
        }
        return "Login successful";
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
