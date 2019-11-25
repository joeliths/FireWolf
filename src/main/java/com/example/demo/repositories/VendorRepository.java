package com.example.demo.repositories;

import com.example.demo.entities.Vendor;
import com.example.demo.models.VendorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
