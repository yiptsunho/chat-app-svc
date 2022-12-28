//package com.example.passwordmanager.passwordProfile;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Configuration
//public class PasswordProfileConfig {
//
//    @Bean
//    CommandLineRunner PasswordProfileCommandLineRunner(PasswordProfileRepository repository) {
//        return args -> {
//            PasswordProfile gitlab = new PasswordProfile(
//                    "gitlab",
//                    "jackyyip@asl.com.hk",
//                    "123456",
//                    1L
//            );
//            PasswordProfile tims = new PasswordProfile(
//                    "tims",
//                    "kccuser",
//                    "123456",
//                    1L
//            );
//
//            repository.saveAll(
//                    List.of(gitlab, tims)
//            );
//        };
//    }
//}
