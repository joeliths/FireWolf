package com.example.demo.services.validation;

import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class ValidationService {

    private final UserRepository userRepository;
    private final VendorRepository vendorRepository;

    public ValidationService(UserRepository userRepository, VendorRepository vendorRepository) {
        this.userRepository = userRepository;
        this.vendorRepository = vendorRepository;
    }

    public void validateThatFieldsAreNotNull(Object... objects){
        if(Stream.of(objects).anyMatch(Objects::isNull)) {
            throw new ValidationException("All fields must be included.");
        }
    }

    public void validateUserExists(String userName) {
        if(userRepository.findByUserName(userName).isEmpty()) {
            throw new ValidationException("No user with user name '" + userName + "' was found.");
        }
    }

    public void validateUserNameIsNotTaken(String userName) {
        if(userRepository.findByUserName(userName).isPresent()) {
            throw new ValidationException("Username '" + userName + "' is already taken.");
        }
    }

    public void validateUserIsVendor(String userName) {
        if(vendorRepository.findByUserName(userName).isEmpty()) {
            throw new ValidationException("User with user name '" + userName + " needs to be a vendor to" +
                    "register a store.");
        }
    }

}
