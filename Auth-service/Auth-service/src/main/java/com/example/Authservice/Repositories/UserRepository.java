package com.example.Authservice.Repositories;

import com.example.Authservice.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser,String> {
}
