package com.example.demo.repositories;

import com.example.demo.entities.InventoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryProductRepository extends JpaRepository<InventoryProduct,Long> {


/*

    @Query("select inventory_product i from inventory_product where i.product_id = :productId")
    InventoryProduct getInventoryProductsBasedOn(@Param("productId")String productId);

*/

   /* List<PostComment> comments = entityManager.createQuery(
            "select pc " +
                    "from PostComment pc " +
                    "where pc.post.id = :postId", PostComment.class)
            .setParameter( "postId", 1L )
            .getResultList();
*/

}
