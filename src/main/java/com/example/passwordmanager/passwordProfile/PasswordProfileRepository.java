package com.example.passwordmanager.passwordProfile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordProfileRepository extends JpaRepository<PasswordProfile, Long> {



}
