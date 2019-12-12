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
    private final CustomerRepository customerRepository;

    public ValidationService(UserRepository userRepository, VendorRepository vendorRepository,
                             CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.vendorRepository = vendorRepository;
        this.customerRepository = customerRepository;
    }

    public void validateThatFieldsAreNotNull(Object... objects){
        if(Stream.of(objects).anyMatch(Objects::isNull)) {
            throw new ValidationException("All fields must be included.");
        }
    }

    public void validateUserExists(String userUuid) {
        if(userRepository.findByUuid(userUuid).isEmpty()) {
            throw new ValidationException("No user with uuid '" + userUuid + "' was found.");
        }
    }

    public void validateUserNameNotTaken(String userName) {
        if(userRepository.findByUserName(userName).isPresent()) {
            throw new ValidationException("Username '" + userName + "' is already taken.");
        }
    }

    public void validateVendorExists(String vendorUuid) {
        if(vendorRepository.findByUuid(vendorUuid).isEmpty()) {
            throw new ValidationException("No vendor with uuid '" + vendorUuid + "' was found.");
        }
    }

}
