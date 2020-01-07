package com.example.demo.services;

import com.example.demo.Mapper.Convert;
import com.example.demo.entities.Store;
import com.example.demo.models.InventoryProductRequestModel;
import com.example.demo.models.StoreModel;
import com.example.demo.repositories.InventoryProductRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.StoreRepository;
import com.example.demo.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import javax.ws.rs.ForbiddenException;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@Transactional
public class VendorService {

    private final VendorRepository vendorRepository;
    private final StoreRepository storeRepository;
    private final InventoryProductRepository inventoryProductRepository;
    private final ProductRepository productRepository;
    private final Convert modelConverter;

    public VendorService(VendorRepository vendorRepository, StoreRepository storeRepository,
                         InventoryProductRepository inventoryProductRepository, Convert modelConverter,
                         ProductRepository productRepository) {
        this.vendorRepository = vendorRepository;
        this.storeRepository = storeRepository;
        this.inventoryProductRepository = inventoryProductRepository;
        this.modelConverter = modelConverter;
        this.productRepository = productRepository;
    }

    public void registerUserAsVendor(String userName) {
        if(isUserAlreadyVendor(userName)) {
            throw new ValidationException();
        }
        vendorRepository.registerVendor(userName);
    }

    public void addStore(String userName, StoreModel store){
        if(!isUserAlreadyVendor(userName)) {
            throw new ValidationException();
        }

        boolean areStoreFieldsInvalid = Stream.of(store.getAddress(), store.getDescription(), store.getPosition(),
                store.getPosition().getLng(), store.getPosition().getLat()).anyMatch(Objects::isNull);
        if(areStoreFieldsInvalid) {
            throw new ValidationException("Invalid fields for store.");
        }

        Store storeToAdd = modelConverter.lowAccessConverter(store, Store.class);
        storeToAdd.setVendor(vendorRepository.getByUserName(userName));
        storeRepository.save(storeToAdd);
    }

    public void addInventoryProductToStore(String userName, String storeUuid, String productUuid,
                                           InventoryProductRequestModel inventoryProduct) {
        if(productRepository.findByUuid(productUuid).isEmpty()) {
            throw new EntityNotFoundException("Could not find product with uuid '" + productUuid + '.');
        }
        if(areInventoryProductFieldsInvalid(inventoryProduct)) {
            throw new ValidationException("Invalid fields for product");
        }
        if(doesStoreNotBelongToVendor(userName, storeUuid)) {
            throw new ValidationException();
        }

        //Todo: add inventory prod to store

    }

    public void updateProductInStore(String userName, String storeUuid, String inventoryProductUuid,
                                     InventoryProductRequestModel updatedProduct) {

        if(doesStoreNotBelongToVendor(userName, storeUuid) ||
                doesInventoryProductNotExistInStore(storeUuid, inventoryProductUuid)) {
            throw new ForbiddenException();
        }

        if(areInventoryProductFieldsInvalid(updatedProduct)) {
            throw new ValidationException("Invalid fields for product.");
        }

        //todo: update prod
    }

    public void removeProductFromStore(String userName, String storeUuid, String inventoryProductUuid) {

        if(doesStoreNotBelongToVendor(userName, storeUuid) ||
                doesInventoryProductNotExistInStore(storeUuid, inventoryProductUuid)) {
            throw new ValidationException();
        }

        //todo: remove prod

    }


    public boolean doesStoreNotBelongToVendor(String userName, String storeUuid) {
        return storeRepository.findByVendorUserName(userName).isEmpty();
        //todo
    }

    public boolean doesInventoryProductNotExistInStore(String storeUuid, String inventoryProductUuid) {
        return inventoryProductRepository
                .findByStoreUuidAndInventoryProductUuid(storeUuid, inventoryProductUuid).isEmpty();
    }

    private boolean isUserAlreadyVendor(String userName) {
        return vendorRepository.findByUserName(userName).isPresent();
    }

    private boolean areInventoryProductFieldsInvalid(InventoryProductRequestModel product) {
        return product.getPrice() < 0 || product.getStock() < 0;
    }




}
