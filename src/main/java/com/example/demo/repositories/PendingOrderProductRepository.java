package com.example.demo.repositories;

import com.example.demo.entities.PendingOrderProduct;
import com.example.demo.models.view.PendingOrderProductView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Set;

public interface PendingOrderProductRepository extends JpaRepository<PendingOrderProduct, Long> {

    @Modifying
    @Query(nativeQuery =  true, value = "INSERT INTO pending_order_product(quantity, uuid, inventory_product_id, pending_order_id) \n" +
            "SELECT :quantity, (SELECT uuid()), (SELECT inventory_product.id FROM inventory_product WHERE inventory_product.uuid = :inventoryUuid), \n" +
            "(SELECT pending_order.id FROM pending_order WHERE pending_order.uuid = :pendingOrderUuid);")
    @Transactional
    int insertPendingOrderProduct(@QueryParam("quantity") int quantity,@QueryParam("inventoryUuid") String inventoryUuid, @QueryParam("pendingOrderUuid") String pendingOrderUuid);

    @Query(nativeQuery = true, value = "SELECT * FROM pending_order_product p WHERE p.pending_order_id = (SELECT id FROM pending_order WHERE uuid = :pendingOrderUuid)")
    List<PendingOrderProduct> getPendingOrderProductByPendingOrderUuid(@QueryParam("pendingOrderUuid")String pendingOrderUuid);

    @Query(nativeQuery = true, value = "SELECT * FROM pending_order_product_view WHERE pending_order_uuid = :uuid")
    Set<PendingOrderProductView> getPendingOrderProductsByPendingOrderUuid(@QueryParam("uuid") String uuid);
//
//    @Query(nativeQuery = true, value = "DELETE FROM pending_order WHERE ")
}
