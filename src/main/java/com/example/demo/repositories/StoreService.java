package com.example.demo.repositories;

import com.example.demo.repositories.jpaRepositories.IStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    IStoreRepository iStoreRepository;

}
