package com.example.demo.services;

import com.example.demo.repositories.IStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    IStoreRepository iStoreRepository;

}
