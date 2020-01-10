package com.example.demo.repositories;

import com.example.demo.entities.Store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;

@Transactional
public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findFirstByAddress(String address);
    @Query(nativeQuery = true, value = "select * from store where store.uuid = :uuid")
    Optional<Store> findByUuid(@Param("uuid") String uuid);

    @Query(nativeQuery = true, value = "select * from store where store.uuid = :uuid")
    Store getByUuid(@Param("uuid") String uuid);

    @Query(nativeQuery = true, value = "SELECT s.* FROM user u JOIN store s ON u.id = s.vendor_id WHERE u.user_name = :username")
    List<Store> getAllStoresByVendorUsername(@QueryParam("username") String username);

    @Query(nativeQuery = true, value = "SELECT * FROM store where vendor_id = " +
            "(SELECT id FROM user WHERE user_name = :userName)")
    Optional<Store> findByVendorUserName(@Param("userName") String userName);

    @Query(nativeQuery = true, value = "SELECT * FROM inventory_product_view WHERE store_uuid = :uuid")
    List getStoreDetailsByUuid(@QueryParam("uuid") String uuid);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM store WHERE vendor_id = (SELECT id FROM user WHERE uuid = :uuid)")
    int removeStoreByVendor(@Param("uuid")String uuid);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM inventory_product WHERE store_id IN (SELECT id FROM store WHERE vendor_id = (SELECT id FROM user WHERE uuid = :uuid))")
    int removeInventoryProductsByVendor(@Param("uuid")String uuid);
}