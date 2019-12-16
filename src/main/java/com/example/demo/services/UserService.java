package com.example.demo.services;

import com.example.demo.Mapper.Convert;
import com.example.demo.entities.Customer;
import com.example.demo.entities.User;
import com.example.demo.models.user.UserRegisterModel;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final Convert modelConverter;

    public UserService(UserRepository userRepository, CustomerRepository customerRepository,
                       PasswordEncoder passwordEncoder, Convert modelConverter) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelConverter = modelConverter;
    }

    public void registerUserAndCustomer(UserRegisterModel userModel){
        validateUserFields(userModel);

        Customer customerToAdd = new Customer();
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        customerToAdd.setUser(modelConverter.lowAccessConverter(userModel, User.class));
        customerRepository.save(customerToAdd);
    }

    public void deleteUser(String userName) {
        userRepository.deleteByUserName(userName);
    }

    public void patchUser(String userName, UserRegisterModel newUserFields) {

        User userToUpdate = userRepository.getByUserName(userName);

        if(newUserFields.getUserName() != null) {
            validateUserNameIsNotTaken(newUserFields.getUserName());
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


    //---Validation---

    private void validateUserFields(UserRegisterModel user) {
        if(Stream.of(user.getUserName(), user.getPassword(), user.getFullName())
                .anyMatch(string -> string == null || string.isBlank())) {
            throw new ValidationException("Missing fields.");
        }
        validateUserNameIsNotTaken(user.getUserName());
    }

    private void validateUserNameIsNotTaken(String userName) {
        if(userRepository.findByUserName(userName).isPresent()) {
            throw new ValidationException("User name '" + userName + " is already taken.");
        }
    }

    private User getUserByUsername(String userName){
        Optional<User> foundUser = userRepository.findByUserName(userName);
        if(foundUser.isEmpty()) {
            throw new EntityNotFoundException("Failed to authenticate. " +
                    "No user with username '" + userName + "' was found.");
        }
        return foundUser.get();
    }

    public boolean checkIfEntityBelongsToUser(String username, long entityUserId){
        long id = getUserByUsername(username).getId();
        return id == entityUserId;
    }


}
