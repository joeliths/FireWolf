package com.example.demo.services;

import com.example.demo.Mapper.Convert;
import com.example.demo.entities.Store;
import com.example.demo.exceptions.customExceptions.WrongOwnerException;
import com.example.demo.models.StoreMapModel;
import com.example.demo.models.StoreModel;
import com.example.demo.models.view.StoreCustomerView;
import com.example.demo.repositories.InventoryProductRepository;
import com.example.demo.repositories.PendingOrderRepository;
import com.example.demo.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StoreService {

    final private StoreRepository storeRepository;
    final private UserService userService;
    final private InventoryProductRepository inventoryProductRepository;
    Convert convert = new Convert();

    @Autowired
    public StoreService(StoreRepository storeRepository, UserService userService,
                        InventoryProductRepository inventoryProductRepository) {
        this.storeRepository = storeRepository;
        this.userService = userService;
        this.inventoryProductRepository = inventoryProductRepository;
    }

    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }

    public Store getStoreByAddress(String address){
        return storeRepository.findFirstByAddress(address);
    }

    public Store getStoreByUuid(String uuid){
        Optional dbResult= storeRepository.findByUuid(uuid);
        if (dbResult.isEmpty()){
            throw  new IllegalStateException("Not Found");
        }
        return (Store) dbResult.get();
    }

    public List<StoreCustomerView> getStoreDetailsByUuid(String uuid){
        if(storeRepository.findByUuid(uuid).isEmpty())
            throw new EntityNotFoundException("Store with uuid "+uuid+" does not exist");

        return storeRepository.getStoreDetailsByUuid(uuid);
    }

    public Set<StoreMapModel> getAllStoresToMap(){
        List<Store> allStores = storeRepository.findAll();
        Set<StoreMapModel> storeMapModelsList = new HashSet<>();
        for (Store store : allStores) {
            StoreMapModel storeMapModel = convert.lowAccessConverter(store, StoreMapModel.class);
            storeMapModelsList.add(storeMapModel);
        }
        return storeMapModelsList;

    }

    public StoreModel getStoreByUuid(String uuid, String username){
        Store store = getStoreEntity(uuid);
        if(userService.checkIfEntityBelongsToUser(username, store.getVendor().getId())){
            return toModel(store);
        }else
            throw new WrongOwnerException("Store with uuid "+uuid+" does not belong to user "+username);
    }

    public Store getStoreEntity(String uuid){
        Optional<Store> store = storeRepository.findByUuid(uuid);
        return store.orElseThrow(() -> new EntityNotFoundException("Store with uuid "+uuid+" cannot be found"));
    }

    public List<StoreModel> getAllStoresByUsername(String username){
        List<Store> stores = storeRepository.getAllStoresByVendorUsername(username);
        return stores.stream().map(s -> toModel(s)).collect(Collectors.toList());
    }

    public StoreModel toModel(Store store){
        return convert.lowAccessConverter(store, StoreModel.class);
    }

    public void deleteStore(String uuid, String name) {
        Store store = getStoreEntity(uuid);
        if(userService.checkIfEntityBelongsToUser(name, store.getVendor().getId())){
            inventoryProductRepository.deleteAllByStore(store);
            storeRepository.delete(store);
        }else
            throw new WrongOwnerException("Store with uuid "+uuid+" does not belong to user "+name);
    }
}
