//package com.example.passwordmanager.user;
//
//import com.example.passwordmanager.passwordProfile.PasswordProfile;
//import com.example.passwordmanager.passwordProfile.PasswordProfileRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class UserConfig {
//
//    @Bean
//    CommandLineRunner UserCommandLineRunner(UserRepository repository) {
//        return args -> {
//            User jacky = new User(
//                    "gitlab",
//                    "jackyyip@asl.com.hk",
//                    "123456"
//            );
//
//            repository.saveAll(
//                    List.of(jacky)
//            );
//        };
//    }
//
//}
