package com.example.Authservice.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor

public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Email
    private String email;
    private String password;


}
