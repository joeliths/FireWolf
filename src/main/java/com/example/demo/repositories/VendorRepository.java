package com.example.demo.repositories;

import com.example.demo.entities.InventoryProduct;
import com.example.demo.entities.PendingOrder;
import com.example.demo.entities.Vendor;
import com.example.demo.models.VendorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VendorRepository extends JpaRepository<Vendor, Long> {


    @Query(nativeQuery = true, value = "select * from vendor v where v.uuid = :uuid")
    Vendor getInventoryproductsByVendor(@Param("uuid") String uuid);


}
