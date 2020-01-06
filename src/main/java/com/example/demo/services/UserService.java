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

    public void registerUserAndCustomer(UserRegisterModel user){
        boolean areUserFieldsInvalid = Stream.of(user.getUserName(), user.getPassword(), user.getFullName())
                .anyMatch(string -> string == null || string.isBlank());
        if(areUserFieldsInvalid || isUserNameTaken(user.getUserName())) {
            throw new ValidationException("Invalid fields for user.");
        }

        Customer customerToAdd = new Customer();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        customerToAdd.setUser(modelConverter.lowAccessConverter(user, User.class));
        customerRepository.save(customerToAdd);
    }

    public void deleteUser(String userName) {
        userRepository.deleteByUserName(userName);
    }

    public void patchUser(String userName, UserRegisterModel newUserFields) {

        User userToUpdate = userRepository.getByUserName(userName);

        if(newUserFields.getUserName() != null) {
            if(isUserNameTaken(newUserFields.getUserName())) {
                throw new ValidationException("Username is taken.");
            }
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



    private boolean isUserNameTaken(String userName) {
        return userRepository.findByUserName(userName).isPresent();
    }

    public boolean checkIfEntityBelongsToUser(String username, long entityUserId){
        User foundUser = userRepository.findByUserName(username)
                .orElseThrow(EntityNotFoundException::new);
        long id = foundUser.getId();
        return id == entityUserId;
    }


}
