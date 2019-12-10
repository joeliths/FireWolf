package com.example.demo.services;

import com.example.demo.models.StoreModel;
import com.example.demo.repositories.VendorRepository;
import com.example.demo.services.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.sql.SQLIntegrityConstraintViolationException;


@Service
public class VendorService {

    private final VendorRepository vendorRepository;
    private final ValidationService validationService;

    public VendorService(VendorRepository vendorRepository, ValidationService validationService) {
        this.vendorRepository = vendorRepository;
        this.validationService = validationService;
    }

    public void registerUserAsVendor(String userUuid){
        vendorRepository.registerVendor(userUuid);
    }

    public void registerStore(String vendorUUID, StoreModel storeModel){
//        boolean fieldsAreMissing = validationService
//                .checkIfAnyFieldsAreNull(storeModel.getAddress(), storeModel.getDescription());
//        if(fieldsAreMissing) {
//            throw new ValidationException("All fields must be included");
//        }

        vendorRepository.registerStore(vendorUUID, storeModel.getAddress(), storeModel.getDescription());
    }

    public void deleteStore(String storeUuid) {

    }
}
