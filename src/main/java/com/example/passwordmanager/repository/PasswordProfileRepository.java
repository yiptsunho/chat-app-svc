package com.example.passwordmanager.repository;

import com.example.passwordmanager.model.PasswordProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PasswordProfileRepository extends JpaRepository<PasswordProfile, Long> {

    List<PasswordProfile> findAllByUserId(String userId);

    boolean existsByAppName(String appName);

    ArrayList<PasswordProfile> findAllByAppNameAndUserId(String appName, Long userId);

//    void delete(Optional<PasswordProfile> passwordToDelete);
}
