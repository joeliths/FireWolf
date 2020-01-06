package com.example.demo.services;

import com.example.demo.Mapper.Convert;
import com.example.demo.entities.InventoryProduct;
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
import javax.ws.rs.NotAllowedException;
import java.util.List;
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
        validateUserIsNotAlreadyVendor(userName);
        vendorRepository.registerVendor(userName);
    }

    public void addStore(String userName, StoreModel storeModel){
        System.out.println(userName);
        validateUserIsVendor(userName);
        validateStoreFields(storeModel);

        Store storeToAdd = modelConverter.lowAccessConverter(storeModel, Store.class);
        storeToAdd.setVendor(vendorRepository.getByUserName(userName));
        storeRepository.save(storeToAdd);
    }

    public void addInventoryProductToStore(String userName, String storeUuid, String productUuid,
                                           InventoryProductRequestModel inventoryProductModel) {
        validateProductExists(productUuid);
        validateInventoryProductFields(inventoryProductModel);
        validateStoreBelongsToVendor(userName, storeUuid);
//        //todo
//        InventoryProduct inventoryProduct = modelConverter
//                .lowAccessConverter(inventoryProductModel, InventoryProduct.class);
//        System.out.println(inventoryProduct.getPrice());
//        System.out.println(inventoryProduct.getStock());
//        System.out.println(inventoryProduct.getProduct().getName());
//        System.out.println(inventoryProduct.getProduct().getDescription());
    }

    public void updateProductInStore(String userName, String storeUuid, String inventoryProductUuid,
                                     InventoryProductRequestModel updatedProductFields) {
        validateStoreBelongsToVendor(userName, storeUuid);
        validateProductExistsInStore(storeUuid, inventoryProductUuid);
    }

    public void removeProductFromStore(String userName, String storeUuid, String inventoryProductUuid) {
        validateStoreBelongsToVendor(userName, storeUuid);
        validateProductExistsInStore(storeUuid, inventoryProductUuid);
    }


    //---Validation--

    private void validateStoreBelongsToVendor(String userName, String storeUuid) {
        if(storeRepository.findByVendorUserName(userName).isEmpty()) {
            throw new ForbiddenException("Cannot modify a store that is not owned.");
        }
    }

    private void validateProductExistsInStore(String storeUuid, String inventoryProductUuid) {
        if(inventoryProductRepository
                .findByStoreUuidAndInventoryProductUuid(storeUuid, inventoryProductUuid).isEmpty()) {
            throw new ForbiddenException("Cannot modify a product that is not owned.");
        }
    }

    private void validateUserIsNotAlreadyVendor(String userName) {
        if(vendorRepository.findByUserName(userName).isPresent()) {
            throw new ForbiddenException("User is already vendor.");
        }
    }

    private void validateUserIsVendor(String userName) {
        if(vendorRepository.findByUserName(userName).isEmpty()) {
            throw new ForbiddenException("Cannot create a store for a user that is not a vendor.");
        }
    }

    private void validateStoreFields(StoreModel store){
        if(Stream.of(store.getAddress(), store.getDescription(), store.getPosition(), store.getPosition().getLng(),
                store.getPosition().getLat())
                .anyMatch(Objects::isNull)) {
            throw new ValidationException("Missing fields.");
        }

    }

    private void validateInventoryProductFields(InventoryProductRequestModel product) {
        if(product.getPrice() < 0) {
            throw new ValidationException("Price can't be a negative value.");
        }
        if(product.getStock() < 0) {
            throw new ValidationException("Stock can't be a negative value.");
        }
    }

    private void validateProductExists(String productUuid) {
        if(productRepository.findByUuid(productUuid).isEmpty()) {
            throw new EntityNotFoundException("Could not find product with uuid '" + productUuid + '.');
        }
    }


}
