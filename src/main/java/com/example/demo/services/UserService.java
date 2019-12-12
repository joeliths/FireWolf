package com.example.demo.services;

import com.example.demo.Mapper.Convert;
import com.example.demo.entities.Customer;
import com.example.demo.entities.User;
import com.example.demo.models.user.UserRequestModel;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.validation.ValidationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ValidationService validationService;
    private final Convert modelConverter = new Convert();

    public UserService(UserRepository userRepository, CustomerRepository customerRepository,
                       PasswordEncoder passwordEncoder, ValidationService validationService) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.validationService = validationService;
    }

    public void registerUserAndCustomer(UserRequestModel userModel){
        validationService.validateThatFieldsAreNotNull(
                userModel.getFullName(), userModel.getUserName(), userModel.getPassword());
        validationService.validateUserNameNotTaken(userModel.getUserName());

        Customer customerToAdd = new Customer();
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        customerToAdd.setUser(modelConverter.lowAccessConverter(userModel, User.class));
        customerRepository.save(customerToAdd);
    }

    public void deleteUserByUUID(String uuid) {
        validationService.validateUserExists(uuid);
        userRepository.deleteByUuid(uuid);
    }

    public void patchUser(String userUuid, UserRequestModel newUserFields) {
        validationService.validateUserExists(userUuid);
        validationService.validateUserNameNotTaken(newUserFields.getUserName());

        User userToUpdate = userRepository.getByUuid(userUuid);

        if(newUserFields.getUserName() != null) {
            userToUpdate.setUserName(newUserFields.getUserName());
        }
        if(newUserFields.getPassword() != null) {
            userToUpdate.setPassword(passwordEncoder.encode(newUserFields.getPassword()));
        }
        if(newUserFields.getFullName() != null) {
            userToUpdate.setFullName(newUserFields.getFullName());
        }

        userRepository.patchUser(userToUpdate);
    }

    //For security
    public User getUserByUserNameReturnEntity(String userName) {
        Optional<User> foundUser = userRepository.findByUserName(userName);
        if(foundUser.isEmpty()) {
            throw new EntityNotFoundException("Failed to authenticate. " +
                    "No user with username '" + userName + "' was found.");
        }
        return foundUser.get();
    }

}
