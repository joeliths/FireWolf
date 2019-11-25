package com.example.demo.services;

import com.example.demo.entities.Store;
import com.example.demo.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    StoreRepository storeRepository;

    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }

    public Store getStoreByAddress(String address){
        return storeRepository.findFirstByAddress(address);
    }

}
