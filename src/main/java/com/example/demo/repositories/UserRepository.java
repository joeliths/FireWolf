package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUserName(String userName);

//
//    @Query(value = "select u from User u where u.userName = :userName fetch join u.roles", nativeQuery = true)
////    @EntityGraph(attributePaths = {"roles"})
//    Optional<User> findByUserNameWithRoles(@Param("userName")String userName);



}
