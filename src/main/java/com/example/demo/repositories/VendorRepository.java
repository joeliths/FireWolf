package com.example.demo.repositories;

import com.example.demo.entities.InventoryProduct;
import com.example.demo.entities.Vendor;
import com.example.demo.models.VendorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO vendor(uuid, user_id) " +
            "VALUES(uuid(), (SELECT id FROM user WHERE user.uuid = :userUuid))")
    void registerVendor(@Param("userUuid") String userUuid);

//    @Modifying
//    @Query(nativeQuery = true, value = "INSERT INTO store(uuid, vendor_id, address, description)" +
//            "VALUES(uuid(), (SELECT id FROM vendor WHERE vendor.uuid = :vendorUuid), :address, :description)")
//    void registerStore(@Param("vendorUuid") String vendorUUID, @Param("address") String address,
//                       @Param("description") String description);

    @Query(nativeQuery = true, value = "select * from inventory_product i where i.store_id = \n" +
            "(select id from store where vendor_id = (select user_id from vendor where vendor_uuid = :vendorUuid))")
    void getPendingOrdersForVendor(@Param("vendorUuid") String vendorUuid);



}
