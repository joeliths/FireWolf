package com.example.demo.services;

import com.example.demo.Mapper.Convert;
import com.example.demo.entities.Product;
import com.example.demo.entities.Store;
import com.example.demo.models.ProductModel;
import com.example.demo.models.StoreMapModel;
import com.example.demo.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
