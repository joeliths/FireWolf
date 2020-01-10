package com.example.demo.repositories;

import com.example.demo.entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO vendor (uuid, user_id) " +
            "VALUES(uuid(), (SELECT id FROM user WHERE user.user_name = :userName))")
    void registerVendor(@Param("userName") String userName);

    @Query(nativeQuery = true, value = "SELECT * FROM vendor where user_id = (SELECT id FROM " +
            "user WHERE user.user_name = :userName)")
    Optional<Vendor> findByUserName(@Param("userName") String userName);

    @Query(nativeQuery = true, value = "SELECT * FROM vendor where user_id = (SELECT id FROM " +
            "user WHERE user.user_name = :userName)")
    Vendor getByUserName(@Param("userName") String userName);

}
