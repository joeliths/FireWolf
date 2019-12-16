package com.example.demo.services;

import com.example.demo.Mapper.Convert;
import com.example.demo.entities.Product;
import com.example.demo.entities.Store;
import com.example.demo.models.ProductModel;
import com.example.demo.models.StoreMapModel;
import com.example.demo.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StoreService {

    final private StoreRepository storeRepository;
    Convert convert = new Convert();

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
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

    public Set<StoreMapModel> getAllStoresToMap(){
        List<Store> allStores = storeRepository.findAll();
        Set<StoreMapModel> storeMapModelsList = new HashSet<>();
        for (Store store : allStores) {
            StoreMapModel storeMapModel = convert.lowAccessConverter(store, StoreMapModel.class);
            storeMapModelsList.add(storeMapModel);
        }
        return storeMapModelsList;

    }

}
