package com.example.demo.repositories;

import com.example.demo.entities.User;
import com.example.demo.entities.helperclasses.MyUUID;
import com.example.demo.models.view.ViewTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "select * from user where user.uuid = :uuid")
    Optional<User> findByUuid(@Param("uuid") String uuid);

    Optional<User> findByUserName(String userName);

    @Modifying
    @Query(nativeQuery = true, value = "delete from user where user.uuid = :uuid")
    void deleteByUuid(@Param("uuid") String uuid);

    ViewTest getById(long id);

}
