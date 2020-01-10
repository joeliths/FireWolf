package com.example.demo.services;

import com.example.demo.Mapper.Convert;
import com.example.demo.entities.*;
import com.example.demo.models.InventoryProductRequestModel;
import com.example.demo.models.StoreModel;
import com.example.demo.repositories.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import javax.ws.rs.ForbiddenException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional
public class VendorService {

    private final VendorRepository vendorRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final InventoryProductRepository inventoryProductRepository;
    private final ProductRepository productRepository;
    private final Convert modelConverter;
    private final UserRoleRepository userRoleRepository;
    private final PositionRepository positionRepository;

    public VendorService(VendorRepository vendorRepository, StoreRepository storeRepository,
                         InventoryProductRepository inventoryProductRepository, Convert modelConverter,
                         ProductRepository productRepository, UserRepository userRepository,
                         UserRoleRepository userRoleRepository,
                         PositionRepository positionRepository) {
        this.vendorRepository = vendorRepository;
        this.storeRepository = storeRepository;
        this.inventoryProductRepository = inventoryProductRepository;
        this.modelConverter = modelConverter;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.positionRepository = positionRepository;
    }

    public void registerUserAsVendor(String userName) {
        if(isUserAlreadyVendor(userName)) {
            throw new ValidationException("User is already vendor.");
        }
        User user = userRepository.findByUserName(userName).get();
        UserRole vendorRole = userRoleRepository.getUserRoleByRole("ROLE_VENDOR").get();
        user.getRoles().add(vendorRole);
        vendorRepository.registerVendor(userName);
    }

    public void addStore(String userName, StoreModel store){
        if(store.getPosition() == null || !isUserAlreadyVendor(userName)) {
            throw new ValidationException();
        }

        boolean areStoreFieldsInvalid = Stream.of(store.getAddress(), store.getDescription(), store.getPosition(),
                store.getPosition().getLng(), store.getPosition().getLat()).anyMatch(Objects::isNull);
        if(areStoreFieldsInvalid) {
            throw new ValidationException("Invalid fields for store.");
        }

        Store storeToAdd = new Store(store.getAddress(), store.getDescription());
        storeToAdd.setVendor(vendorRepository.getByUserName(userName));
        Position position = new Position(store.getPosition().getLat(), store.getPosition().getLng());
        position.setStore(storeToAdd);

        positionRepository.save(position);
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

        InventoryProduct inventoryProductToAdd = modelConverter
                .lowAccessConverter(inventoryProduct, InventoryProduct.class);
        inventoryProductToAdd.setProduct(productRepository.getByUuid(productUuid));
        inventoryProductToAdd.setStore(storeRepository.getByUuid(storeUuid));
        inventoryProductRepository.save(inventoryProductToAdd);

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

        InventoryProduct prodToUpdate = inventoryProductRepository.findByUuid(inventoryProductUuid)
                .orElseThrow(() -> new EntityNotFoundException("Could not find selected inventory product."));

        if(updatedProduct.getStock() != null) {
            prodToUpdate.setStock(updatedProduct.getStock());
        }
        if(updatedProduct.getPrice() != null) {
            prodToUpdate.setPrice(updatedProduct.getPrice());
        }

        inventoryProductRepository.patchInventoryProduct(prodToUpdate);
    }

    public void removeProductFromStore(String userName, String storeUuid, String inventoryProductUuid) {

        if(doesStoreNotBelongToVendor(userName, storeUuid) ||
                doesInventoryProductNotExistInStore(storeUuid, inventoryProductUuid)) {
            throw new ValidationException();
        }

        inventoryProductRepository.deleteByUuid(inventoryProductUuid);
    }
    
    public boolean doesStoreNotBelongToVendor(String userName, String storeUuid) {
        Optional<Store> store = storeRepository.findByUuid(storeUuid);
        return store.map(value -> !value.getVendor().getUser().getUserName().equals(userName)).orElse(true);
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
