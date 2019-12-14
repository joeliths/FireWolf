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

    @Modifying
    @Query(nativeQuery = true,
            value = "update user set uuid = :#{#user.uuid.toString()}, user_name = :#{#user.userName}, " +
                    "full_name = :#{#user.fullName}, password = :#{#user.password} where id = :#{#user.id}")
    void patchUser(@Param("user") User user);

    Optional<User> findByUserName(String userName);

    User getByUserName(String userName);

    void deleteByUserName(String userName);


    ViewTest getById(long id);

}
