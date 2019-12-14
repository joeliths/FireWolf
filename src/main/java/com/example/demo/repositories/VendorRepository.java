package com.example.demo.repositories;

import com.example.demo.entities.InventoryProduct;
import com.example.demo.entities.User;
import com.example.demo.entities.Vendor;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

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

//
//    @Modifying
//    @Query(nativeQuery = true, value = "INSERT INTO store(uuid, vendor_id, address, description)" +
//            "VALUES(uuid(), (SELECT user_id FROM vendor WHERE vendor.uuid = :vendorUuid), :address, :description)")
//    void registerStore(@Param("vendorUuid") String vendorUUID, @Param("address") String address,
//                       @Param("description") String description);
//
//    @Query(nativeQuery = true, value = "select * from inventory_product i where i.store_id = \n" +
//            "(select id from store where vendor_id = (select user_id from vendor where vendor_uuid = :vendorUuid))")
//    Set<InventoryProduct> getInventoryProductsOfAStoreOfAVendor(@Param("vendorUuid") String vendorUuid);
//
//    @Query(nativeQuery = true, value = "select * from vendor where vendor.uuid = :uuid")
//    Optional<Vendor> findByUuid(@Param("uuid") String uuid);
//
//    @Query(nativeQuery = true, value = "select * from vendor where vendor.uuid = :uuid")
//    Vendor getByUuid(@Param("uuid") String uuid);



}
