package com.example.Login.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UsersData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private long phone;
    private String username;

    // Custom constructors
    public UsersData(String email, String password, long phone, String username) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.username = username;
    }

    public UsersData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UsersData() {
    }
}
