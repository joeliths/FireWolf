package com.example.demo.repositories;

import com.example.demo.models.VendorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVendorRepository extends JpaRepository<VendorModel, Long> {
}
