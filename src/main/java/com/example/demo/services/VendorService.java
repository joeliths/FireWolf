package com.example.demo.services;

import com.example.demo.Mapper.Convert;
import com.example.demo.entities.Store;
import com.example.demo.models.StoreModel;
import com.example.demo.repositories.StoreRepository;
import com.example.demo.repositories.VendorRepository;
import com.example.demo.services.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class VendorService {

    private final VendorRepository vendorRepository;
    private final StoreRepository storeRepository;
    private final ValidationService validationService;
    private final Convert modelConverter = new Convert();

    public VendorService(VendorRepository vendorRepository, ValidationService validationService,
                         StoreRepository storeRepository) {
        this.vendorRepository = vendorRepository;
        this.validationService = validationService;
        this.storeRepository = storeRepository;
    }

    public void registerUserAsVendor(String userName) {
        validationService.validateUserExists(userName);
        vendorRepository.registerVendor(userName);
    }

    public void registerStore(String userName, StoreModel storeModel){
        validationService.validateUserIsVendor(userName);
        validationService.validateThatFieldsAreNotNull(storeModel.getAddress(), storeModel.getDescription());

        Store storeToAdd = modelConverter.lowAccessConverter(storeModel, Store.class);
        storeToAdd.setVendor(vendorRepository.getByUserName(userName));
        storeRepository.save(storeToAdd);
    }

}
