package com.example.demo.repositories;

import com.example.demo.entities.InventoryProduct;
import com.example.demo.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;
import java.util.Optional;
import java.util.Set;

public interface InventoryProductRepository extends JpaRepository<InventoryProduct,Long> {

    @Query(value = "select inventory_product i from inventory_product where i.product_id = :productId",
    nativeQuery = true
    )
    Set<InventoryProduct> getInventoryProductsBasedOn(@Param("productId")String productId);

    @Query(value = "select inventory_product i from inventory_product where i.store_id = :storeId",
            nativeQuery = true
    )
    Set<InventoryProduct> getStoreProductsBasedOn(@Param("storeId")String storeId);

    @Query(nativeQuery = true, value = "select * from inventory_product p where p.uuid = :uuid")
    Optional<InventoryProduct> findByUuid(@Param("uuid") String uuid);

   @Query(nativeQuery = true, value = "SELECT * FROM inventory_product i WHERE (i.uuid = :inventoryProductUuid AND " +
           "i.store_id = (SELECT id FROM store WHERE store.uuid = :storeUuid))")
    Optional<InventoryProduct> findByStoreUuidAndInventoryProductUuid(@Param("storeUuid") String storeUuid,
                                           @Param("inventoryProductUuid") String inventoryProductUuid);

   @Modifying
   @Transactional
   @Query(nativeQuery = true, value = "DELETE FROM inventory_product WHERE store_id = (SELECT id FROM store WHERE uuid = :storeUuid)")
    int deleteInventoryProductsByStoreUuid(@QueryParam("storeUuid")String storeUuid);

   @Transactional
   void deleteAllByStore(Store store);

    @Modifying
    @Query(nativeQuery = true,
            value = "update inventory_product set uuid = :#{#inventoryProduct.uuid.toString()}, " +
                    "price = :#{#inventoryProduct.price}, stock = :#{#inventoryProduct.stock} " +
                    "where id = :#{#inventoryProduct.id}")
    void patchInventoryProduct(@Param("inventoryProduct") InventoryProduct inventoryProduct);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM inventory_product WHERE uuid = :uuid")
    void deleteByUuid(String uuid);

    @Query(nativeQuery = true, value = "SELECT stock FROM inventory_product WHERE uuid = :uuid")
    int getInventoryProductByStock(@Param("uuid")String uuid);

}
