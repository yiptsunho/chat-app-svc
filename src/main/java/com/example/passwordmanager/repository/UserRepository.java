package com.example.passwordmanager.repository;

import com.example.passwordmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByLoginId(String loginId);

    User findByLoginId(String loginId);
}
