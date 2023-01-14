package com.example.passwordmanager.passwordProfile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface PasswordProfileRepository extends JpaRepository<PasswordProfile, Long> {

    List<PasswordProfile> findAllByUserId(String userId);

    boolean existsByAppName(String appName);

    ArrayList<PasswordProfile> findAllByAppNameAndUserId(String appName, Long userId);

//    void delete(Optional<PasswordProfile> passwordToDelete);
}
