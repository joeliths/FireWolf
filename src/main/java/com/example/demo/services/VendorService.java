package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.entities.Vendor;
import com.example.demo.models.StoreModel;
import com.example.demo.models.VendorModel;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class VendorService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    VendorRepository vendorRepository;

    public boolean registerUserAsVendor(String userUuid) {
        Optional<User> foundUser = userRepository.findByUuid(userUuid);
        if(foundUser.isEmpty()) {
            //Todo
        }
        Vendor vendor = new Vendor();
        vendor.setUser(foundUser.get());
        vendorRepository.save(vendor);
        return true;
    }

    public String registerStore(StoreModel storeModel){
        return "not yet implemented";
    }
}
