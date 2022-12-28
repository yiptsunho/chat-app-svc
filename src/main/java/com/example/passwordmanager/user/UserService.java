package com.example.passwordmanager.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(User user) {
        return userRepository.existsByUserName(user.getUserName());
    }

    public String createNewUser(User user) {
        return "userName: " + user.getUserName() + " password: " + user.getPassword();
    }

    public String editUser(User user) {
        return "userName: " + user.getUserName() + " password: " + user.getPassword();
    }

}
