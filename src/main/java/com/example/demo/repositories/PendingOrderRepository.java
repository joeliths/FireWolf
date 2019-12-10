package com.example.demo.repositories;

import com.example.demo.entities.PendingOrder;
import com.example.demo.entities.PendingOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

public interface PendingOrderRepository extends JpaRepository<PendingOrder,Long> {

    @Query(nativeQuery = true, value = "select * from pending_order p where p.uuid = :uuid")
    Optional<PendingOrder> findByUuid(@Param("uuid") String uuid);


    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "INSERT INTO pending_order(expiration_date_time, placement_date_time, uuid, user_id, store_id) \n" +
            "SELECT :expirationDate, :placementDate, (SELECT uuid()), (SELECT customer.user_id FROM customer WHERE customer.uuid = :customerUuid),\n" +
            "(SELECT store.id FROM store WHERE store.uuid = :storeUuid);")
    @Transactional
    int insertPendingOrder(@Param("expirationDate") Date expirationDate, @Param("placementDate") Date placementDate,
                                           @Param("customerUuid") String customerUuid,
                                           @Param("storeUuid") String storeUuid);

    
}
