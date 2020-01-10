package com.example.demo.repositories;

import com.example.demo.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> getUserRoleByRole(String role);

}
