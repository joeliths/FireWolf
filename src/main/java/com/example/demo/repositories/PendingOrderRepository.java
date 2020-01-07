package com.example.demo.repositories;

import com.example.demo.entities.PendingOrder;
import com.example.demo.entities.PendingOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.QueryParam;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PendingOrderRepository extends JpaRepository<PendingOrder,Long> {


    @Query(value = "Select * from pending_order limit 1",nativeQuery = true)
    PendingOrder getTesting();

    @Query(nativeQuery = true, value = "select * from pending_order p where p.uuid = :uuid")
    PendingOrder findFirstByUuid(@Param("uuid") String uuid);


    @Query(nativeQuery = true, value = "select * from pending_order p where p.uuid = :uuid")
    Optional<PendingOrder> findByUuid(@Param("uuid") String uuid);


    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "INSERT INTO pending_order(expiration_date_time, placement_date_time, uuid, customer_id, store_id) \n" +
            "SELECT (SELECT now()), (SELECT now()), (SELECT uuid()), (SELECT id FROM user u WHERE u.user_name = :username),\n" +
            "(SELECT store.id FROM store WHERE store.uuid = :storeUuid);")
    @Transactional
    int insertPendingOrder(
            @Param("username") String username,
            @Param("storeUuid") String storeUuid);

    @Query(nativeQuery = true, value = "SELECT * FROM pending_order WHERE id = LAST_INSERT_ID();")
    PendingOrder getLatestPendingOrder();

    @Query(nativeQuery = true, value = "SELECT po.* FROM user u " +
            "JOIN pending_order po ON u.id = po.customer_id " +
            "WHERE u.user_name = :username")
    List<PendingOrder> getPendingOrderByCustomer(@QueryParam("username") String username);

    @Query(nativeQuery = true, value = "SELECT * FROM pending_order WHERE store_id = (SELECT id from STORE WHERE uuid = :storeUuid);")
    List<PendingOrder> getPendingOrderByStore(@QueryParam("storeUuid")String storeUuid);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM pending_order WHERE store_id = (SELECT id from store WHERE uuid = :storeUuid);" )
    int deletePendingOrdersByStore(@Param("storeUuid")String storeUuid);





}
