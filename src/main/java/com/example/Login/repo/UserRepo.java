package com.example.Login.repo;

import com.example.Login.model.UsersData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UsersData,Integer> {
    boolean existsByEmail(String email);
    boolean existsByPhone(long phone);
    Optional<UsersData> findByEmail(String email);
    Optional<UsersData> findByUsername(String username);
}
