package com.example.demo.services;

import com.example.demo.Mapper.Convert;
import com.example.demo.entities.PendingOrder;
import com.example.demo.entities.Product;
import com.example.demo.entities.Store;
import com.example.demo.exceptions.customExceptions.WrongOwnerException;
import com.example.demo.models.ProductModel;
import com.example.demo.models.StoreMapModel;
import com.example.demo.models.StoreModel;
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

    Convert convert = new Convert();

    @Autowired
    public StoreService(StoreRepository storeRepository, UserService userService) {
        this.storeRepository = storeRepository;
        this.userService = userService;
    }

    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }

    public Store getStoreByAddress(String address){
        return storeRepository.findFirstByAddress(address);
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
            storeRepository.delete(store);
        }else
            throw new WrongOwnerException("Store with uuid "+uuid+" does not belong to user "+name);
    }
}
