package com.example.demo.repositories;

import com.example.demo.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> getUserRoleByRole(String role);

//    @Query(value = "select r.role from user_role r where id = :userId", nativeQuery = true)
//    Optional<List<UserRole>>getUserRolesByUserId(@Param("userId")long userId);
}
